package com.cloud.alibaba.config.controller;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.cloud.alibaba.config.config.UserConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
public class ConfigController {
    @Resource
    private UserConfig userConfig;
    @Resource
    private NacosConfigManager nacosConfigManager;
    @Resource
    private Environment environment;
    @Value("${user.name:zz}")
    private String userName;
    @Value("${user.age:25}")
    private Integer age;

    @GetMapping(value = "/user")
    public String simple() {
        return "Hello Nacos Config! " + "Hello " + userName + ", " + age + " [UserConfig]: "
                + userConfig + " ! " + nacosConfigManager.getConfigService();
    }

    @GetMapping(value = "/get/{name}")
    public String getValue(@PathVariable String name) {
        return String.valueOf(environment.getProperty(name));
    }

    @GetMapping(value = "/bool")
    public boolean bool() {
        return (Boolean) (userConfig.getMap().get("2"));
    }

}

