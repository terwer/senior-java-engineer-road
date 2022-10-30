package space.terwer;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction3 extends ActionSupport{
	private String username;
	private String password;

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
	
	@Override
	public void validate() {
		if(StringUtils.isBlank(this.getUsername())) {
			this.addFieldError("username", "username required");
		}
		if(StringUtils.isBlank(this.getPassword())) {
			this.addFieldError("password", "password required");
		}
	}

	public String execute() {
		if("hello".equals(this.getUsername().trim()) && "world".equals(this.getPassword().trim())){
			return "success";
		}
		
		this.addFieldError("username", "username or password error");
		return "failer";
	}
}
