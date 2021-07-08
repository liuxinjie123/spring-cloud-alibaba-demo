package com.cloud.alibaba.config.client3377.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/config")
public class ConfigClientController {
//    @Value("${config.info}")
//    private String configInfo;
//
//    @GetMapping(value = "/info")
//    public String getConfigInfo() {
//        return "config info: " + configInfo;
//    }

}
