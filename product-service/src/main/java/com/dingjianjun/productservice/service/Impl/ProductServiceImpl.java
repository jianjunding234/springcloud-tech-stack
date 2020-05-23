package com.dingjianjun.productservice.service.Impl;

import com.dingjianjun.productservice.dao.ProductInfoMapper;
import com.dingjianjun.productservice.dto.ProductDto;
import com.dingjianjun.productservice.dto.RespDto;
import com.dingjianjun.productservice.entity.ProductInfo;
import com.dingjianjun.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/23
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductInfoMapper productInfoMapper;

    @Override
    public RespDto<Boolean> add(ProductDto productDto) {
        Date now = new Date();
        ProductInfo record = ProductInfo.builder().productNo(productDto.getProductNo())
                .productName(productDto.getProductName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .status(1).gmtCreate(now).gmtModify(now).build();
        productInfoMapper.insert(record);
        return RespDto.ok(Boolean.TRUE);
    }

    @Override
    public RespDto<Boolean> update(String productNo, Integer amount) {
        ProductInfo productInfo = productInfoMapper.selectByProductNo(productNo);
        if (null != productInfo && productInfo.getStock() >= amount) {
            productInfo.setStock(productInfo.getStock() - amount);
            productInfoMapper.updateByPrimaryKey(productInfo);
            return RespDto.ok(Boolean.TRUE);
        }

        return RespDto.error(100, "库存不足");
    }
}
