package com.dingjianjun.orderservice.controller;

import com.dingjianjun.orderservice.dto.OrderCreateDto;
import com.dingjianjun.orderservice.dto.RespDto;
import com.dingjianjun.orderservice.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/24
 */
@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/hi")
    public String test() {
        return orderService.hi();
    }

    /**
     * 分布式事务
     * @param record
     * @return
     */
    @PostMapping("/create")
    @GlobalTransactional(rollbackFor = Exception.class)
    public RespDto<Boolean> create(@RequestBody OrderCreateDto record) {
        return orderService.create(record);
    }



}
