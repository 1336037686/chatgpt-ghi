package com.ghi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统代理配置类
 * @Auther: LGX
 * @Date: 2023/3/24 23:49
 * @Description: SystemProxyConfig
 * @Version 1.0.0
 */
@Component
@Data
public class SystemProxyConfig {

    /**
     * ip
     */
    @Value("${app.proxy.ip}")
    private String ip;

    /**
     * 端口
     */
    @Value("${app.proxy.port}")
    private Integer port;

}
