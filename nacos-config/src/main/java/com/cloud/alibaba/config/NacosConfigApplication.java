package com.cloud.alibaba.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NacosConfigApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NacosConfigApplication.class, args);
        String userName = context.getEnvironment().getProperty("user.name");
        String userAge = context.getEnvironment().getProperty("user.age");
        System.out.println("user name: " + userName + ", user age: " + userAge);
    }

}
