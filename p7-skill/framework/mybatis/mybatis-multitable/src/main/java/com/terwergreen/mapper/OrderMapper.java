package com.terwergreen.mapper;

import com.terwergreen.pojo.Order;

import java.util.List;

/**
 * 订单映射
 *
 * @name: IUserMapper
 * @author: terwer
 * @date: 2022-03-17 17:54
 **/
public interface OrderMapper {
    /**
     * 查询订单同时查询订单所属用户
     * @return
     */
    public List<Order> findOrderAndUser();
}
