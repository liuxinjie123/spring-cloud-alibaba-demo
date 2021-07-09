package com.cloud.alibaba.consumer8003.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyMvcConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
