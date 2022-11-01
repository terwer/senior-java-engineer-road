package space.terwer.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts2.util.StrutsTypeConverter;

import space.terwer.bean.User;

public class UserConverter3 extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		List<User> list = new ArrayList<>();

		for (String value : values) {
			StringTokenizer st = new StringTokenizer(value, ";");

			User user = new User();

			String username = st.nextToken();
			String pasword = st.nextToken();

			user.setUsername(username);
			user.setPassword(pasword);

			list.add(user);
		}

		return list;
	}

	@Override
	public String convertToString(Map context, Object o) {
		List<User> list = (List) o;

		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (int i = 0; i < list.size(); i++) {
			User user = list.get(i);
			sb.append("{username=" + user.getUsername());
			sb.append(",password=" + user.getPassword());
			sb.append("}");

			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");

		return sb.toString();
	}

}
