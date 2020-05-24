package com.dingjianjun.controller;

import com.dingjianjun.service.HealthStatusService;
import com.dingjianjun.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author : Jianjun.Ding
 * @description: 服务访问控制器
 * @date 2020/4/9
 */
@RestController
@Slf4j
public class MainController {
    @Autowired
    private HealthStatusService healthStatusService;
    @Resource
    private HelloService helloService;


    @GetMapping("/hi")
    public Object getHi() {
        // 负载均衡器选择一个可用的服务（status为UP）
//        ServiceInstance instance = loadBalancerClient.choose("service-provider");
        //String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hi";
        // Eureka 注册中心注册的服务是REST风格，HTTP协议
//        log.info("uri:{}", instance.getUri());
        return helloService.getHi("fxz");

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


    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus().toString();
    }


}
