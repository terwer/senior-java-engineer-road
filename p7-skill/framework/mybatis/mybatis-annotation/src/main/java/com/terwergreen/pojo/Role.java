package com.terwergreen.pojo;

/**
 * 角色
 *
 * @name: Role
 * @author: terwer
 * @date: 2022-05-12 14:14
 **/
public class Role {
    private Integer id;
    private String rolename;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
