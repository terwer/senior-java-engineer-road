package space.terwer.model;

import java.util.Date;

/**
 * 人员
 *
 * @author terwer
 * @name Person
 * @date 2023-01-03 00:40
 **/
public class Person {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Date registerDate;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
