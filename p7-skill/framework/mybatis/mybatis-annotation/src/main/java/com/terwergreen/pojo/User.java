package com.terwergreen.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 用户
 *
 * @name: User
 * @author: terwer
 * @date: 2022-05-25 13:25
 **/
public class User implements Serializable {
    private Integer id;
    private String username;

    // 代表当前用户具备那些订单
    private List<Order> orderList;


    // 代表当前用户具备的那些角色
    private List<Role> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", orderList=" + orderList +
                ", roleList=" + roleList +
                '}';
    }
}
