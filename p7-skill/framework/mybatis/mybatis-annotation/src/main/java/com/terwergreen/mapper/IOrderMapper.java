package com.terwergreen.mapper;

import com.terwergreen.pojo.Order;
import com.terwergreen.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单映射
 *
 * @name: IUserMapper
 * @author: terwer
 * @date: 2022-03-17 17:54
 **/
public interface IOrderMapper {
    /**
     * 查询订单同时查询订单所属用户
     *
     * @return
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderTime", column = "ordertime"),
            @Result(property = "total", column = "total"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "com.terwergreen.mapper.IUserMapper.findUserById"))
    })
    @Select("select * from orders")
    public List<Order> findOrderAndUser();
}
