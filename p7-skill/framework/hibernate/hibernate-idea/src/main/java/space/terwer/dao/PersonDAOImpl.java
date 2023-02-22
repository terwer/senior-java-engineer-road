package space.terwer.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import space.terwer.model.Person;
import space.terwer.util.HibernateUtil;

import java.util.List;

/**
 * PersonDAO实现类
 *
 * @author terwer
 * @name PersonDAOImpl
 * @date 2023-01-03 01:02
 **/
public class PersonDAOImpl implements IPersonDAO {
    @Override
    public void savePerson(Person person) {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(person);

            tx.commit();
        } catch (Exception e) {
            if (null != tx) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public List<Person> listAllPersons() {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        List<Person> list = null;
        try {
            // Person是类名，不是表名
            Query query = session.createQuery("from Person");
            list = (List<Person>) query.list();
        } catch (Exception e) {

        } finally {
            HibernateUtil.closeSession(session);
        }
        return list;
    }
}
