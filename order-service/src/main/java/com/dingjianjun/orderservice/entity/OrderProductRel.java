package com.dingjianjun.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductRel implements Serializable {
    private Long id;

    private String orderNo;

    private String productNo;

    private Integer buyNum;

    private Date gmtCreate;

    private Date gmtModify;
}