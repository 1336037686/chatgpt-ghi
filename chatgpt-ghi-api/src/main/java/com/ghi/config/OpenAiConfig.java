package com.ghi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAi配置
 * @author LGX_TvT <br>
 * @version 1.0 <br>
 * Create by 2023-03-27 09:46 <br>
 * @description: OpenAiConfig <br>
 */
@Configuration
@ConfigurationProperties(prefix = "open-ai.config")
@Data
public class OpenAiConfig {

    /**
     * api-key
     */
    private String apiKey;

    /**
     * organization-name
     */
    private String organizationName;

    /**
     * organization-id
     */
    private String organizationId;

}
