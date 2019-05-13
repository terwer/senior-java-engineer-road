package testp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import testp.beans.Person;

/**
 * IOCInitTest3
 *
 * @author Terwer
 * @version 1.0
 * 2019/5/6 18:04
 **/
public class IOCInitTest3 {
    public static void main(String[] args) {
        String xmlPath ="C:\\Users\\Terwer\\IdeaProjects\\senior-java-engineer-road\\src\\main\\java\\com\\terwergreen\\springsourceexplain\\iocinit\\src\\main\\resources\\applicationContext.xml";
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext(xmlPath);

        Person p1 = (Person) applicationContext.getBean("person");
        String result = p1.sayHello("Test3");
        System.out.println(result);
    }
}
