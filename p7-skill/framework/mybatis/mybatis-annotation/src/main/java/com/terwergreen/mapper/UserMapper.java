package com.terwergreen.mapper;

import com.terwergreen.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户映射
 *
 * @name: UserMapper
 * @author: terwer
 * @date: 2022-05-25 13:27
 **/
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();
}
