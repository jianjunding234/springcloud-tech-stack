package com.dingjianjun.service.impl;

import com.dingjianjun.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/24
 */
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "hello", threadPoolKey = "threadpool-fxz", threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "10")

    })
    public String getHi(String name) {
        String url = "http://service-provider/hi";
        String responseStr = restTemplate.getForObject(url, String.class);
        return responseStr;
    }

    private String hello(String name, Throwable ex) {
        log.info("{}", ex.getMessage());
        return "熔断->fallback, name:" + name;

    }

}
