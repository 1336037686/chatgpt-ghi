package com.ghi.chatgptghi;

import com.ghi.config.OpenAiConfig;
import com.ghi.config.SystemProxyConfig;
import com.ghi.module.spiderRecord.domain.SpiderRecord;
import com.ghi.module.spiderRecord.service.SpiderRecordService;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.net.Proxy;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ChatgptGhiApplicationTests {


    @Test
    void contextLoads() throws InterruptedException {

    }

}
