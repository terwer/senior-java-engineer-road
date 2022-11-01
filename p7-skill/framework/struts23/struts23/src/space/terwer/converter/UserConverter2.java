package space.terwer.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import space.terwer.bean.User;

public class UserConverter2 extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String firstValue = values[0];

		String[] st = firstValue.split(";");

		String uername = st[0];
		String password = st[1];

		User user = new User();
		user.setUsername(uername);
		user.setPassword(password);

		return user;
	}

	@Override
	public String convertToString(Map context, Object o) {
		User user = (User) o;

		String username = user.getUsername();
		String password = user.getPassword();

		String userInfo = "Use StrutsTypeConverter converted from UserConverter2=>username: " + username
				+ ", password: " + password;
		return userInfo;
	}

}
