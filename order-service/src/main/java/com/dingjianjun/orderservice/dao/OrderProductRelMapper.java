package com.dingjianjun.orderservice.dao;

import com.dingjianjun.orderservice.entity.OrderProductRel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderProductRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderProductRel record);

    int insertSelective(OrderProductRel record);

    OrderProductRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderProductRel record);

    int updateByPrimaryKey(OrderProductRel record);
}