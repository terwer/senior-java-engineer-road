package com.terwergreen.mapper;

import com.terwergreen.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色映射
 *
 * @name: IRoleMapper
 * @author: terwer
 * @date: 2022-09-06 00:04
 **/
public interface  IRoleMapper {
    @Select("select * from sys_role r,sys_user_role ur where r.id=ur.roleid and ur.userid=#{userId}")
    List<Role> findRolesByUserId(Integer userId);
}
