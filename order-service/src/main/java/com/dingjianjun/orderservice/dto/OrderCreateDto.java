package com.dingjianjun.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {
    private String orderNo;

    private Long userId;

    private BigDecimal money;

    private String payNo;

    private String productNo;

    private Integer amount;
}
