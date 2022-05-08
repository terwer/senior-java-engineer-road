package com.terwergreen


/**
 * UserKt
 *
 * @name: UserKt
 * @author: terwer
 * @date: 2022-05-08 15:31
 **/
class UserKt {
    var id: Int? = null
    var name: String? = null
    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}'
    }
}