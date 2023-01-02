package space.terwer.service;

import space.terwer.dao.IPersonDAO;
import space.terwer.dao.PersonDAOImpl;
import space.terwer.model.Person;

/**
 * 用户业务实现类
 *
 * @author terwer
 * @name PersonServiceImpl
 * @date 2023-01-03 01:50
 **/
public class PersonServiceImpl implements IPersonService {
    @Override
    public void savePerson(Person person) {
        IPersonDAO personDAO = new PersonDAOImpl();
        personDAO.savePerson(person);
    }
}
