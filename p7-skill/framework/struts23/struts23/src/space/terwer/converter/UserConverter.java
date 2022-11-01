package space.terwer.converter;

import java.util.Map;

import ognl.DefaultTypeConverter;
import space.terwer.bean.User;

public class UserConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		// 从页面向后台对象转换
		if (User.class == toType) {
			String[] str = (String[]) value;
			String firstValue = str[0];

			String[] st = firstValue.split(";");

			String uername = st[0];
			String password = st[1];

			User user = new User();
			user.setUsername(uername);
			user.setPassword(password);

			return user;
		}

		// 从后台对象到页面转换
		else if (String.class == toType) {
			User user = (User) value;

			String username = user.getUsername();
			String password = user.getPassword();

			String userInfo = "username: " + username + ", password: " + password;
			return userInfo;
		}
		return null;
	}
}
