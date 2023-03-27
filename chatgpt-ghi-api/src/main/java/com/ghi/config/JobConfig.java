package com.ghi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 定时任务配置
 * @author LGX_TvT <br>
 * @version 1.0 <br>
 * Create by 2023-03-27 08:55 <br>
 * @description: JobConfig <br>
 */
@Configuration
@Data
public class JobConfig {

    /**
     * 停用定时任务列表
     */
    public static List<String> DISABLE_JOBS;

    static {

        InputStream inputStream = JobConfig.class.getClassLoader().getResourceAsStream("job.properties");

        Properties props = new Properties();
        try {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("load job.properties error");
        }
        Object disableJobsObj = props.get("job.config.disableJobs");
        if (Objects.isNull(disableJobsObj)) DISABLE_JOBS = new ArrayList<>();
        else DISABLE_JOBS = Arrays.stream(disableJobsObj.toString().split(";")).collect(Collectors.toList());
    }




    /**
     * 判断是否禁用
     * @param jobName 任务名称
     * @return bool
     */
    public static boolean isDisable(String jobName) {
      return !JobConfig.DISABLE_JOBS.contains(jobName);
    }



}
