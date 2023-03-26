package com.ghi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * OpenAi配置
 * @Auther: LGX
 * @Date: 2023/3/25 13:42
 * @Description: ChatGptConfig
 * @Version 1.0.0
 */
@Component
@Data
public class OpenAiConfig {

    @Value("${openAi.config.apiKey}")
    private String apiKey;

    @Value("${openAi.config.organizationName}")
    private String organizationName;

    @Value("${openAi.config.organizationId}")
    private String organizationId;


}
