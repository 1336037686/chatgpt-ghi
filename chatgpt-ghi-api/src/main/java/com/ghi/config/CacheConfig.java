package com.ghi.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 缓存配置类
 * @author LGX_TvT <br>
 * @version 1.0 <br>
 * Create by 2023-03-30 09:27 <br>
 * @description: CacheConfig <br>
 */
@ConfigurationProperties(prefix = "cache.config")
@Configuration
@Data
public class CacheConfig {

    /**
     * 最后一次写入或访问后，指定经过多长的时间过期
     */
    private Integer expireAfterAccess = 3600;

    /**
     * 最后一次写入后，指定经过多长的时间缓存过期
     * expireAfterWrite 和 expireAfterAccess同时存在时，以expireAfterWrite为准
     */
    private Integer expireAfterWrite = 3600;

    /**
     * 初始的缓存空间大小
     */
    private Integer initialCapacity = 100;

    /**
     * 缓存的最大条数
     */
    private Long maximumSize = 10000L;

    @Bean
    public Cache<String, Object> caffeineCache() {
        return Caffeine.newBuilder()
                // 设置最后一次写入或访问后1个小时后过期
                .expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS)
                // 设置最后一次写入或访问后1个小时后过期
                .expireAfterAccess(expireAfterAccess, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(initialCapacity)
                // 缓存的最大条数
                .maximumSize(maximumSize)
                .build();
    }

}
