package com.dingjianjun.orderservice.service;

import com.dingjianjun.orderservice.dto.OrderCreateDto;
import com.dingjianjun.orderservice.dto.OrderDto;
import com.dingjianjun.orderservice.dto.RespDto;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/24
 */
public interface OrderService {
    RespDto<Boolean> create(OrderCreateDto record);

    String hi();
}
