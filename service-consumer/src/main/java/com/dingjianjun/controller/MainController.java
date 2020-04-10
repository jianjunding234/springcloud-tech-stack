package com.dingjianjun.controller;

import com.google.common.collect.Lists;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.netflix.appinfo.InstanceInfo.InstanceStatus.UP;

/**
 * @author : Jianjun.Ding
 * @description: 服务访问控制器
 * @date 2020/4/9
 */
@RestController
@Slf4j
public class MainController {
//    @Autowired
//    private EurekaDiscoveryClient discoveryClient;

    @Resource
    private EurekaClient client;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping("/hi")
    public Object getHi() {
        // 负载均衡器选择一个可用的服务（status为UP）
        ServiceInstance instance = loadBalancerClient.choose("service-provider");
        //String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hi";
        // Eureka 注册中心注册的服务是REST风格，HTTP协议
        RestTemplate template = new RestTemplate();
        log.info("uri:{}", instance.getUri());
        String responseStr = template.getForObject(instance.getUri() + "/hi", String.class);
        return responseStr;

//        List<InstanceInfo> instanceInfos = client.getInstancesByVipAddress("service-provider", false);
//        Optional.ofNullable(instanceInfos).orElse(Lists.newArrayList()).stream()
//                .filter(o -> Objects.nonNull(o) && Objects.equals(UP, o.getStatus()))
//                .forEach(instance -> {
//                    String url = "http://" + instance.getHostName() + ":" + instance.getPort() + "/hi";
//                    // Eureka 注册中心注册的服务是REST风格，HTTP协议
//                    RestTemplate template = new RestTemplate();
//                    String responseStr = template.getForObject(url, String.class);
//                    log.info(responseStr);
//                });
//
//        return "ok";
    }
}
