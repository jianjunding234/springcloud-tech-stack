package com.dingjianjun.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private Long id;

    private String productNo;

    private String productName;

    private BigDecimal price;

    private Integer status;

    private Integer stock;

    private Date gmtCreate;

    private Date gmtModify;
}