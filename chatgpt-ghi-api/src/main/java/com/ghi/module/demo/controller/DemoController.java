package com.ghi.module.demo.controller;

import com.ghi.config.OpenAiConfig;
import com.ghi.config.SystemProxyConfig;
import com.ghi.module.job.openApiExplain.GhiStreamListener;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.listener.SseStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import okhttp3.sse.EventSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import java.net.Proxy;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: LGX
 * @Date: 2023/3/25 14:07
 * @Description: DemoController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    private SystemProxyConfig systemProxyConfig;
    @Resource
    private OpenAiConfig openAiConfig;

    @GetMapping("hello")
    public String hello() {
        AtomicReference<String> res = new AtomicReference<>("");
        CountDownLatch countDownLatch = new CountDownLatch(1);

        //国内需要代理 国外不需要
        Proxy proxy = Proxys.http("127.0.0.1", 1080);
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .timeout(600)
                .apiKey(openAiConfig.getApiKey())
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build()
                .init();

        GhiStreamListener listener = new GhiStreamListener();
        Message message = Message.of("能帮我写一段使用Jsoup调用ChatGPT API的代码吗？");

        listener.setOnComplate(msg -> {
            //回答完成，可以做一些事情
            System.out.println("--------------------------");
            System.out.println("回答完成，可以做一些事情");
            System.out.println(msg);
            res.set(msg);
            countDownLatch.countDown();
        });
        chatGPTStream.streamChatCompletion(Arrays.asList(message), listener);

        System.out.println("start...");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return res.get();
    }

}
