package space.terwer.struts23;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction2 extends ActionSupport {
	private String username;
	private String password;
	private Integer age;
	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String myExecute() throws Exception {
		System.out.println("myExecute called");
		return SUCCESS;
	}
}
