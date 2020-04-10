package com.dingjianjun.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Jianjun.Ding
 * @description: 服务访问控制器
 * @date 2020/4/9
 */
@RestController
@Slf4j
public class MainController {

    @GetMapping("/hi")
    public Object getHi() {
        log.info("hi");
        return "hi";
    }
}
