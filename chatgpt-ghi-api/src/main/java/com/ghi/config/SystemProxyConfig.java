package com.ghi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 系统代理配置类
 * @Auther: LGX
 * @Date: 2023/3/24 23:49
 * @Description: SystemProxyConfig
 * @Version 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "app.proxy")
@Data
public class SystemProxyConfig {

    /**
     * 是否开启代理
     */
    private Boolean enable;

    /**
     * ip
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

}
