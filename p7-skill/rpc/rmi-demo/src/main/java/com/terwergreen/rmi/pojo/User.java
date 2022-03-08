package com.terwergreen.rmi.pojo;

import java.io.Serializable;

/**
 * 用户类
 *
 * @name: User
 * @author: terwer
 * @date: 2022-03-06 01:31
 **/
public class User implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
