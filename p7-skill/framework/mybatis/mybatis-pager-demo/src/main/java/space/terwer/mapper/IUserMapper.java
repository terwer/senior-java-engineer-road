package space.terwer.mapper;

import space.terwer.pojo.User;

import java.util.List;

/**
 * @author terwer on 2024/6/13
 */
public interface IUserMapper {
    /**
     * 查询用户
     */
    List<User> findAll();
}