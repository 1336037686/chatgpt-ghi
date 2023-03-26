package com.ghi.chatgptghi;

import com.ghi.config.OpenAiConfig;
import com.ghi.config.SystemProxyConfig;
import com.ghi.module.issue.domain.Issue;
import com.ghi.module.issue.mapper.IssueMapper;
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

    @Resource
    private IssueMapper issueMapper;

    @Test
    void contextLoads() throws InterruptedException {
        Issue issue = new Issue();
        issue.setKey("1");
        issue.setIssue("1");
        issue.setAnswer("1");

        issueMapper.insert(issue);
    }

}
