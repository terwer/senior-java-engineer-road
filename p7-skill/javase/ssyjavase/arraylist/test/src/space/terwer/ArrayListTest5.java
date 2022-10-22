package space.terwer;

import java.util.ArrayList;

/**
 * 自定义类使用ArrayList
 * 
 * @author Administrator
 * 
 */
public class ArrayListTest5 {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(new Point(2, 3));
		list.add(new Point(2, 2));
		list.add(list);
		list.add(new Point(4, 4));

		// for (int i = 0; i < list.size(); i++) {
		// System.out.println((Point) list.get(i));
		// }

		System.out.println(list.toString());
	}

}