package space.terwer.struts23;

import com.opensymphony.xwork2.ActionSupport;

import space.terwer.bean.User;

public class UserAction extends ActionSupport {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("username:" + this.getUser().getUsername());
		System.out.println("password:" + this.getUser().getPassword());
		
		return SUCCESS;
	}
}
