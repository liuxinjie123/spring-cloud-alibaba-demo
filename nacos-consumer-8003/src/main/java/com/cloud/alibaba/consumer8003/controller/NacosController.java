package com.cloud.alibaba.consumer8003.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class NacosController {
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    @Value(value = "${spring.application.name}")
    private String applicationName;

    private final String SERVICE_PROVIDER_9001 = "nacos-discovery";

    @GetMapping(value = "/echo/app-name")
    public String getApplicationName() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(SERVICE_PROVIDER_9001);
        String path = String.format("http://$s:%s/echo/$s", serviceInstance.getHost(), serviceInstance.getPort(), applicationName);
        System.out.println("request path: " + path);
        return restTemplate.getForObject(path, String.class);
    }

    @GetMapping(value = "/hello")
    public String hello(String name) {
        // <1> 获得服务 `eureka-provider` 的一个实例
        ServiceInstance instance;
        if (true) {
            // 获取服务 `demo-provider` 对应的实例列表
            List<ServiceInstance> clientInstances = discoveryClient.getInstances(SERVICE_PROVIDER_9001);
            // 选择第一个
            instance = clientInstances.size() > 0 ? clientInstances.get(0) : null;
        } else {
            // TODO 获取不到实例
            instance = loadBalancerClient.choose(SERVICE_PROVIDER_9001);
        }
        // <2> 发起调用
        if (instance == null) {
            throw new IllegalStateException("获取不到实例");
        }
        //  返回结果
        String path = String.format("http://%s:%s/echo/%s", instance.getHost(), instance.getPort(), applicationName);
        System.out.println("request path: " + path);
        return restTemplate.getForObject(path, String.class);
    }

}
