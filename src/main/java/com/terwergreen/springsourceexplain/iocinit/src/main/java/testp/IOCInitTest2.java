package testp;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import testp.beans.Person;

/**
 * IOCInitTest2
 *
 * @author Terwer
 * @version 1.0
 * 2019/5/6 18:00
 **/
public class IOCInitTest2 {
    public static void main(String[] args) {
        // 定位
        ClassPathResource resource = new ClassPathResource("applicationContext.xml");
        // 载入
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 注册
        reader.loadBeanDefinitions(resource);

        // 从beanFactory获取bean
        Person p1 = (Person) beanFactory.getBean("person");
        String result = p1.sayHello("Green");
        System.out.println(result);
    }
}
