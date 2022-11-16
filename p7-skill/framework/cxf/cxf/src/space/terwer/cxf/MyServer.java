package space.terwer.cxf;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class MyServer {

	public static void main(String[] args) {
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		
		factory.setServiceClass(MyServiceImpl.class);
		factory.setAddress("http://10.10.10.40:8080/myservice?wsdl");
		
		Server server = factory.create();
		server.start();
	}

}
