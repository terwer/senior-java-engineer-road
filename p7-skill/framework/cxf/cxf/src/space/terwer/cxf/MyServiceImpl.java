package space.terwer.cxf;

public class MyServiceImpl implements MyService {

	@Override
	public String hello(String username) {
		System.out.println("hello is invoked!");
		return "hello," + username;
	}

}
