package com.dingjianjun.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1577917829183551229L;
    private Long id;

    private String orderNo;

    private Long userId;

    private BigDecimal money;

    private Integer status;

    private String payNo;

    private Date gmtCreate;

    private Date gmtModify;
}