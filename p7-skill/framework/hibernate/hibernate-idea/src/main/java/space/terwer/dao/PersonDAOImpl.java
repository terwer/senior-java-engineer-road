package space.terwer.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import space.terwer.model.Person;
import space.terwer.util.HibernateUtil;

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
}
