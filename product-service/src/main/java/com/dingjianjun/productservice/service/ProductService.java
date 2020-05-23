package com.dingjianjun.productservice.service;

import com.dingjianjun.productservice.dto.ProductDto;
import com.dingjianjun.productservice.dto.RespDto;

/**
 * @author : Jianjun.Ding
 * @description: 商品服务接口
 * @date 2020/5/23
 */
public interface ProductService {
    RespDto<Boolean> add(ProductDto productDto);

    RespDto<Boolean> update(String productNo, Integer amount);
}
