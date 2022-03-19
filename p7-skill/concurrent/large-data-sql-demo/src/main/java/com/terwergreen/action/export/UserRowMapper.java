package com.terwergreen.action.export;

import com.terwergreen.pojo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @name: UserRowMapper
 * @author: terwer
 * @date: 2022-03-19 18:12
 **/
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        Integer id = rs.getInt("id");
        user.setId(id);
        String username = rs.getString("username");
        user.setUsername(username);
        String password = rs.getString("password");
        user.setPassword(password);
        Integer age = rs.getInt("age");
        user.setAge(age);
        Integer sex = rs.getInt("sex");
        user.setSex(sex);

        return user;
    }
}
