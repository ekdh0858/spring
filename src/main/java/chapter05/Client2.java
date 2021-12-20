package chapter05;

import org.springframework.stereotype.Component;

// 빈 객체의 초기화와 소멸에 관여는 하고 싶은데 
// 상황에 따라서 InitializingBean, DisposableBean 인터페이스를 구현하지 못하는 경우도 있음
// 스프링 설정 클래스에서 직접 초기화와 소멸에 관여하는 메서드를 지정할 수 있음

@Component
public class Client2 {
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void send() {
		System.out.println("send to "+host);
	}

	public void connect() {
		System.out.println("connect 호출");
	}
	
	public void close() {
		System.out.println("close 호출");
	}

}
