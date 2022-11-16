package space.terwer.cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Client {

	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setAddress("http://110.10.10.400:8080/myservice?wsdl");
		factory.setServiceClass(MyService.class);

		MyService myservice = (MyService) factory.create();
		String result = myservice.hello("terwer");

		System.out.println("result=>" + result);
	}

}
