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
 * @name: IUserMapper
 * @author: terwer
 * @date: 2022-05-25 13:27
 **/
public interface IUserMapper {
    /**
     * 查询用户
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 新增用户
     */
    @Insert("insert into user(username) values(#{username})")
    void add(User user);

    /**
     * 更新用户
     */
    @Update("update user set username=#{username} where id=#{id}")
    void update(User user);

    /**
     * 删除用户
     */
    @Delete("delete from user where id=#{id}")
    void delete(Integer id);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);
}
