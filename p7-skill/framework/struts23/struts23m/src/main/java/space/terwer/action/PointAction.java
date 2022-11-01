package space.terwer.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import space.terwer.bean.Point;

public class PointAction extends ActionSupport {
	private Point point;
	private Integer age;
	private String username;
	private Date date;

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
