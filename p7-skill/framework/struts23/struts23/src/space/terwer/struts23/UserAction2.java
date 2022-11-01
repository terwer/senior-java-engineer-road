package space.terwer.struts23;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import space.terwer.bean.User;

public class UserAction2 extends ActionSupport {

	private List<User> user;

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		for (User u : this.getUser()) {
			System.out.println(u.getUsername() + "," + u.getPassword());
		}

		return SUCCESS;
	}

}
