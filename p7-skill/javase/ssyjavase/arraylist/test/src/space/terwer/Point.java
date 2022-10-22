package space.terwer;

/**
 * 坐标
 * 
 * @author terwer
 * 
 */
public class Point {
	int x;
	int y;

	/**
	 * 坐标的构造的方法
	 * 
	 * @param x
	 *            x
	 * @param y
	 *            y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return x和y的坐标
	 */
	public String toString() {
		return "x=" + this.x + ",y=" + this.y;
	}
}