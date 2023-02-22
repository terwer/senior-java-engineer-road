package space.terwer.action;

import com.opensymphony.xwork2.ActionSupport;
import space.terwer.service.IPersonService;
import space.terwer.service.PersonServiceImpl;
import space.terwer.model.Person;

import java.util.Date;

/**
 * 人员处理
 *
 * @author terwer
 * @name PersonAction
 * @date 2023-01-03 00:55
 **/
public class PersonAction extends ActionSupport {
    private String username;
    private String password;
    private Integer age;

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

    public String savePerson() throws Exception {
        Person person = new Person();
        person.setUsername(username);
        person.setPassword(password);
        person.setAge(age);

        person.setRegisterDate(new Date());

        IPersonService personService = new PersonServiceImpl();
        personService.savePerson(person);



        return SUCCESS;
    }
}
