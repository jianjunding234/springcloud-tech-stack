package com.dingjianjun.orderservice.service.impl;

import com.dingjianjun.orderservice.dao.OrderInfoMapper;
import com.dingjianjun.orderservice.dao.OrderProductRelMapper;
import com.dingjianjun.orderservice.dto.OrderCreateDto;
import com.dingjianjun.orderservice.dto.OrderDto;
import com.dingjianjun.orderservice.dto.RespDto;
import com.dingjianjun.orderservice.entity.OrderInfo;
import com.dingjianjun.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/24
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private OrderProductRelMapper orderProductRelMapper;

    @Override
    public RespDto<Boolean> create(OrderCreateDto record) {
        // 处理订单
        processOrder(record);
        // 减少商品库存
        reductStock(record.getProductNo(), record.getAmount());

        return RespDto.ok(Boolean.TRUE);
    }

    @Override
    public String hi() {
        return restTemplate.getForEntity("http://product-service/product/hi", String.class).getBody();
    }

    private Boolean processOrder(OrderCreateDto record) {
        Date now = new Date();
        OrderInfo orderInfo = OrderInfo.builder().orderNo(record.getOrderNo())
                .money(record.getMoney()).payNo(record.getPayNo())
                .userId(record.getUserId()).status(1).gmtCreate(now).gmtModify(now)
                .build();
        orderInfoMapper.insert(orderInfo);
        return Boolean.TRUE;
    }

    private RespDto<Boolean> reductStock(String productNo, Integer amount) {
        String uri = "http://product-service/product/reductStock?productNo=" + productNo + "&amount=" + amount;
        return restTemplate.getForEntity(uri, RespDto.class).getBody();
    }

}
