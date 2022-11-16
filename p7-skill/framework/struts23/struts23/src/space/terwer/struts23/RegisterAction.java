package space.terwer.struts23;

import java.util.Calendar;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	private String username;
	private String password;
	private String repassword;
	private Integer age;
	private Date birthday;
	private Date graduation;

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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getGraduation() {
		return graduation;
	}

	public void setGraduation(Date graduation) {
		this.graduation = graduation;
	}

	@Override
	public void validate() {
		// System.out.println("validate incoked");

		if (null == this.getUsername() || this.getUsername().length() < 4 || this.getUsername().length() > 6) {
			this.addActionError("username invalid");
			
			this.addFieldError("username", "username invalid in field");
			this.addFieldError("username", "username invalid in field2");
		}

		if (null == this.getPassword() || this.getPassword().length() < 4 || this.getPassword().length() > 6) {
			this.addActionError("password invalid");
		} else if (null == this.getRepassword() || this.getRepassword().length() < 4
				|| this.getRepassword().length() > 6) {
			this.addActionError("repassword invalid");
		} else if (!this.getPassword().equals(this.getRepassword())) {
			this.addActionError("the passwords not same");
		}

		if (this.getAge() < 10 || this.getAge() > 50) {
			this.addActionError("age invalid");
		}

		if (null == this.getBirthday()) {
			this.addActionError("birthday invalid");
		}

		if (null == this.getGraduation()) {
			this.addActionError("graduation invalid");
		}

		if (null != this.getBirthday() && null != this.getGraduation()) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(this.getBirthday());

			Calendar c2 = Calendar.getInstance();
			c2.setTime(this.getGraduation());

			if (c1.after(c2)) {
				this.addActionError("birthday should be before graduation");
			}
		}

		// 注意：这样不行
		// this.getFieldErrors().clear();
		// this.getActionErrors().clear();
		
		// 下面的代码可以直接清空错误，直接跳转
		// this.clearFieldErrors();
		// this.clearActionErrors();
		
		// System.out.println("error cleared");
	}

	@Override
	public String execute() throws Exception {
		// System.out.println("execute invoked");
		return SUCCESS;
	}
}
