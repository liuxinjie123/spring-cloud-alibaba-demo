package com.cloud.alibaba.config.client8002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigClient_8002 {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClient_8002.class, args);
    }

}
