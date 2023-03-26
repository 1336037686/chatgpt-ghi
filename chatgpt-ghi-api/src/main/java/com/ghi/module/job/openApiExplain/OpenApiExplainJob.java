package com.ghi.module.job.openApiExplain;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ghi.config.OpenAiConfig;
import com.ghi.config.SystemProxyConfig;
import com.ghi.module.repository.domain.Repository;
import com.ghi.module.repository.service.RepositoryService;
import com.ghi.module.repositoryIntro.domain.RepositoryIntro;
import com.ghi.module.repositoryIntro.service.RepositoryIntroService;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.util.Proxys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: LGX
 * @Date: 2023/3/25 14:22
 * @Description: OpenApiExplainJob
 * @Version 1.0.0
 */
@Component
@Slf4j
public class OpenApiExplainJob {

    // 抓取时间 每天的6点，14点，20点 5分
    private static final String CRON = "0 10 6,14,20 * * ?";
    // 重试次数
    private static final Integer RETRY_NUM = 3;
    // 重试间隔时间
    private static final Integer RETRY_INTERVAL = 10;
    // 每次请求休眠时间
    private static final Integer SLEEP = 5;
    // 问题集
    private static final List<String> ISSUSES = Arrays.asList(
            "能用中文介绍下${repoUrl}项目吗？",
            "能用中文分析下${repoUrl}的实现细节吗？",
            "能用中文描述一下怎么使用${repoUrl}项目吗？"
    );
    @Resource
    private SystemProxyConfig systemProxyConfig;
    @Resource
    private OpenAiConfig openAiConfig;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RepositoryIntroService repositoryIntroService;

    @Transactional
    @Scheduled(cron = CRON)
    public void job() {
        log.info("--------- OpenApiExplainJob START ---------");
        // 查找所有没被描述过的项目
        List<Repository> repositories = repositoryService.list(new LambdaQueryWrapper<Repository>()
                .eq(Repository::getIsIntro, "0")
                .orderByDesc(Repository::getCreateTime)
        );
        log.info("需要描述的项目数量：{}", repositories.size());
        List<RepositoryIntro> res = new ArrayList<>();
        for (Repository repository : repositories) {
            for (String issus : ISSUSES) {
                boolean execSuccess = false;
                int exexIndex = 1;
                String newIssue = issus.replace("${repoUrl}", repository.getRepoUrl());

                // 开始获取答案，失败会进行重试
                while (!execSuccess && exexIndex <= RETRY_NUM) {
                    log.info("开始获取答案, 获取次数：{}", exexIndex);
                    try {
                        RepositoryIntro chatGptAnswer = getChatGptAnswer(repository, newIssue);
                        res.add(chatGptAnswer);
                        execSuccess = true;
                        log.info("获取成功!");
                    } catch (Exception e) {
                        log.error("获取失败...");
                        log.error(e.getMessage());
                    }
                    exexIndex += 1;
                    // 重试睡眠时间
                    if (!execSuccess && exexIndex <= RETRY_NUM) {
                        try {
                            TimeUnit.SECONDS.sleep(RETRY_INTERVAL);
                        } catch (InterruptedException ignored) {}
                        log.error("准备重试...");
                    }
                }

                // 最终还是失败
                if (!execSuccess) {
                    RepositoryIntro repositoryIntro = new RepositoryIntro().setIssue(newIssue).setAnswer(null).setStatus("1").setRepoId(repository.getId());
                    res.add(repositoryIntro);
                }

                // 进行下一次请求睡眠
                // 重试睡眠时间
                try {
                    TimeUnit.SECONDS.sleep(SLEEP);
                } catch (InterruptedException ignored) {}
            }
        }
        for (Repository repository : repositories) repository.setIsIntro("1");
        // 保存仓库数据
        repositoryService.updateBatchById(repositories);
        // 保存回答数据
        repositoryIntroService.saveBatch(res);
        log.info("--------- OpenApiExplainJob END ---------");
    }

    /**
     * 获取chatgpt 回答
     * @return /
     */
    public RepositoryIntro getChatGptAnswer(Repository repository, String issue) throws Exception {
        log.info("getChatGptAnswer: {}, {}", issue, JSON.toJSONString(repository));
        AtomicReference<String> res = new AtomicReference<>("");
        AtomicReference<String> status = new AtomicReference<>("0");
        AtomicReference<Boolean> execSuccess = new AtomicReference<>(false);
        // 使用锁监听回答结束时机
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //国内需要代理 国外不需要
        Proxy proxy = Proxys.http(systemProxyConfig.getIp(), systemProxyConfig.getPort());
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .timeout(6000).apiKey(openAiConfig.getApiKey())
                .proxy(proxy).apiHost("https://api.openai.com/")
                .build().init();
        // 构建监听器
        GhiStreamListener listener = new GhiStreamListener();

        log.info("问题：{}", issue);
        Message message = Message.of(issue);
        // 回答完成，可以做一些事情
        listener.setOnComplate(msg -> {
            log.info("回答结束");
            res.set(msg);
            status.set("0");
            execSuccess.set(true);
            countDownLatch.countDown();
        });
        // 获取异常回调
        listener.setOnError((throwable, response) -> {
            log.error("回答获取异常");
            status.set("1");
            execSuccess.set(false);
            countDownLatch.countDown();
        });
        chatGPTStream.streamChatCompletion(Arrays.asList(message), listener);
        // 线程阻塞等待回答完毕
        countDownLatch.await();
        // 拼装数据
        RepositoryIntro repositoryIntro = new RepositoryIntro()
                .setIssue(issue)
                .setAnswer(res.get())
                .setStatus(status.get())
                .setRepoId(repository.getId());
        return repositoryIntro;
    }


}
