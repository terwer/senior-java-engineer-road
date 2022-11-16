package space.terwer.cxf;

import javax.jws.WebService;

@WebService
public interface MyService {
	String hello(String username);
}
