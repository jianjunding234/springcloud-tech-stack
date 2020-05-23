package com.dingjianjun.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Jianjun.Ding
 * @description: 商品Dto
 * @date 2020/5/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private String productNo;

    private String productName;

    private BigDecimal price;

    private Integer status;

    private Integer stock;

    private Date gmtCreate;

    private Date gmtModify;
}
