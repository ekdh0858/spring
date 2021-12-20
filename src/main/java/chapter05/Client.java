package chapter05;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Client implements InitializingBean, DisposableBean{
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void send() {
		System.out.println("send to "+host);
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Client 객체 소멸");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Client 객체 생성");
	}

}
