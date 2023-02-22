package space.terwer.dao;

import space.terwer.model.Person;

import java.util.List;

/**
 * PersonDAO接口
 *
 * @author terwer
 * @name IPersonDAO
 * @date 2023-01-03 01:01
 **/
public interface IPersonDAO {
    void savePerson(Person person);

    List<Person> listAllPersons();
}
