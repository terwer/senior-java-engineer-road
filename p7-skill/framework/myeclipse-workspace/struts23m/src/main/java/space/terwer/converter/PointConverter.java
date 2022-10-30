package space.terwer.converter;

import java.util.Map;

import ognl.DefaultTypeConverter;
import space.terwer.bean.Point;

public class PointConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		if (Point.class == toType) {
			Point point = new Point();

			String[] str = (String[]) value;
			String[] paramValues = str[0].split(",");

			Integer x = new Integer(paramValues[0]);
			Integer y = new Integer(paramValues[1]);

			point.setX(x);
			point.setY(y);

			return point;
		}

		if (String.class == toType) {
			Point point = (Point) value;

			int x = point.getX();
			int y = point.getY();

			String result = "[x=" + x + ", y=" + y + "]";

			return result;
		}

		return null;
	}

}
