package com.ghi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatgptGhiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ChatgptGhiApplication.class, args);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        System.out.println("=====================================");
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
