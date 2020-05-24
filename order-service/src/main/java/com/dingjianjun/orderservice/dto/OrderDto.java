package com.dingjianjun.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;

    private String orderNo;

    private Long userId;

    private BigDecimal money;

    private Integer status;

    private String payNo;

    private Date gmtCreate;

    private Date gmtModify;
}
