package com.ghi.module.job.gitHubTrendingSpider;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.ghi.config.SystemProxyConfig;
import com.ghi.module.repository.domain.Repository;
import com.ghi.module.repository.service.RepositoryService;
import com.ghi.module.spiderRecord.domain.SpiderRecord;
import com.ghi.module.spiderRecord.service.SpiderRecordService;
import com.ghi.module.spiderRepo.domain.SpiderRepo;
import com.ghi.module.spiderRepo.service.SpiderRepoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * github 趋势爬虫 定时任务
 *
 * @Auther: LGX
 * @Date: 2023/3/25 11:24
 * @Description: GitHubTrendingSpiderJob
 * @Version 1.0.0
 */
@ConditionalOnExpression("#{T(com.ghi.config.JobConfig).isDisable('com.ghi.module.job.gitHubTrendingSpider.GitHubTrendingSpiderJob')}")
@Component
@Slf4j
public class GitHubTrendingSpiderJob {

    // 抓取时间 每天的6点，14点，20点
    private static final String CRON = "0 0 6,14,20 * * ?";
    // 重试次数
    private static final Integer RETRY_NUM = 3;
    // 重试间隔时间
    private static final Integer RETRY_INTERVAL = 10;

    @Resource
    private SystemProxyConfig systemProxyConfig;
    @Resource
    private SpiderRecordService spiderRecordService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private SpiderRepoService spiderRepoService;

    @Transactional
    @Scheduled(cron = CRON)
    public void job() {
        log.info("--------- GitHubTrendingSpiderJob START ---------");
        List<Repository> gitHubTrendingData = new ArrayList<>();
        boolean execSuccess = false;
        int exexIndex = 1;
        while (!execSuccess && exexIndex <= RETRY_NUM) {
            log.info("开始抓取, 抓取次数：{}", exexIndex);
            try {
                gitHubTrendingData = this.getGitHubTrendingData();
                execSuccess = true;
                log.info("抓取成功!");
            } catch (IOException e) {
                log.error("抓取失败...");
                log.error(e.getMessage());
            }
            exexIndex += 1;
            // 重试睡眠时间
            if (!execSuccess && exexIndex <= RETRY_NUM) {
                try {
                    TimeUnit.SECONDS.sleep(RETRY_INTERVAL);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.error("准备重试...");
            }
        }
        //打印日志
        if (execSuccess) {
            log.info("数据抓取成功，size：{}", gitHubTrendingData.size());
            for (Repository repository : gitHubTrendingData) log.info("repo: {}", JSON.toJSONString(repository));
        }
        else log.error("数据抓取失败");
        // 保存数据
        String key = UUID.randomUUID().toString().replace("-", "");
        SpiderRecord spiderRecord = new SpiderRecord().setKey(key).setStatus(execSuccess ? "1" : "0");
        spiderRecordService.save(spiderRecord);
        if (execSuccess && CollectionUtil.isNotEmpty(gitHubTrendingData)) {
            // 执行成功保存抓取数据
            repositoryService.saveBatch(gitHubTrendingData);
            // 保存中间关系
            List<SpiderRepo> relation = new ArrayList<>();
            for (Repository repository : gitHubTrendingData) {
                SpiderRepo spiderRepo = new SpiderRepo().setSpiderRecordId(spiderRecord.getId()).setRepoId(repository.getId());
                relation.add(spiderRepo);
            }
            if (CollectionUtil.isNotEmpty(relation)) spiderRepoService.saveBatch(relation);
        }
        log.info("--------- GitHubTrendingSpiderJob END ---------");
    }


    /**
     * 抓取github 趋势数据
     * @return /
     * @throws IOException
     */
    public List<Repository> getGitHubTrendingData() throws IOException {
        List<Repository> res = new ArrayList<>();
        log.debug("--------- GitHubTrendingSpider ---------");
        String url = "http://github.com/trending";
        try {
            // 发送GET请求以获取GitHub Trending页面的HTML代码
            Document doc = Jsoup.connect(url)
                    .proxy(systemProxyConfig.getIp(), systemProxyConfig.getPort())
                    .get();

            // 使用JSoup解析HTML代码，并从中提取项目数据
            Elements repoList = doc.select("article.Box-row");
            for (int i = 0; i < repoList.size(); i++) {
                String repoTitle = repoList.get(i).select("h1 a").text();
                String repoUrl = "https://github.com" + repoList.get(i).select("h1 a").attr("href");
                String repoDescription = repoList.get(i).select("p").text();
                String repoLanguage = repoList.get(i).select("[itemprop=programmingLanguage]").text();
                String repoStars = repoList.get(i).select(".octicon-star").get(2).parents().get(0).text().trim();
                String repoForks = repoList.get(i).select(".octicon-repo-forked").parents().get(0).text().trim();
                Integer repoStarsNum = StringUtils.isBlank(repoStars) ? 0 : Integer.parseInt(repoStars.replace(",", ""));
                Integer repoForksNum = StringUtils.isBlank(repoForks) ? 0 : Integer.parseInt(repoForks.replace(",", ""));
                // 输出提取的项目数据
                log.debug("Repo: " + repoTitle);
                log.debug("URL: " + repoUrl);
                log.debug("Description: " + repoDescription);
                log.debug("Language: " + repoLanguage);
                log.debug("Stars: " + repoStars);
                log.debug("Forks: " + repoForks);
                log.debug("-----------------------------");

                Repository repository = new Repository()
                        .setRepoTitle(repoTitle).setRepoUrl(repoUrl).setRepoDesc(repoDescription)
                        .setRepoLanguage(repoLanguage).setStars(repoStarsNum).setForks(repoForksNum).setIsIntro("0");
                res.add(repository);
            }
            return res;
        } catch (IOException e) {
            throw e;
        }
    }
}
