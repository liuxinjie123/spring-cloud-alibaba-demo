package com.cloud.alibaba.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class NacosController {
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate restTemplate;

    @Value(value = "${spring.application.name}")
    private String applicationName;

    @GetMapping(value = "/app-name")
    public String getApplicationName() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String path = String.format("http://$s:%s/echo/$s", serviceInstance.getHost(), serviceInstance.getPort(), applicationName);
        System.out.println("request path: " + path);
        return restTemplate.getForObject(path, String.class);
    }

}
