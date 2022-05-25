package com.terwergreen.mapper;

import com.terwergreen.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Insert("insert into user(username) values(#{username})")
    void add(User user);

    @Update("update user set username=#{username} where id=#{id}")
    void update(User user);

    @Delete("delete from user where id=#{id}")
    void delete(Integer id);
}
