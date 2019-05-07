package testp;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import testp.beans.Person;

/**
 * IOCInitTest
 *
 * @author Terwer
 * @version 1.0
 * 2019/5/6 17:16
 **/
public class IOCInitTest {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("applicationContext.xml");
        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
        Person p1 = (Person) beanFactory.getBean("chinese");
        String result = p1.sayHello("Terwer");
        System.out.println(result);

        Person p2 = (Person) beanFactory.getBean("america");
        String result2 = p2.sayHello("Green");
        System.out.println(result2);

    }
}
