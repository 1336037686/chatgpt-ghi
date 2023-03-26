package com.ghi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 应用全局配置类
 * @Auther: LGX
 * @Date: 2023/3/24 23:47
 * @Description: ApplicationGlobalConfig
 * @Version 1.0.0
 */
// 开启定时任务配置
@EnableScheduling
// 引入hutool SpringUtil工具类
@Import(cn.hutool.extra.spring.SpringUtil.class)
@Configuration
public class ApplicationGlobalConfig {

}
