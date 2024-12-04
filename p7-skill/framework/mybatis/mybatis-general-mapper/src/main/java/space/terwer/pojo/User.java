package space.terwer.pojo;

import io.mybatis.provider.Entity;

/**
 * @author terwer on 2024/12/4
 */
@Entity.Table("user")
public class User {
    @Entity.Column(id = true)
    private Integer id;
    private String username;

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
}
