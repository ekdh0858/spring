package chapter05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 스프링 컨테이너의 라이프 사이클
// 1. 초기화 : 설정 클래스에서 정보를 읽어와 알맞은 빈 객체를 생성하고 알맞는 의존 주입을 함
// 2. 종료 : 컨테이너를 다 사용했다면 컨테이너를 종료 해야함 / 컨테이너가 갖고 있던 빈 객체들도 사라짐
// 스프링 컨테이너의 라이프 사이클은 개발자가 관리함
// 빈 객체의 라이프 사이클은 스프링 컨테이너가 관리함
// 스프링 컨테이너가 빈 객체를 초기화, 소멸 할 때 컨테이너가 갖고 있는 메서드를 활용해서
// 초기화, 소멸 시킴
// 스프링 컨테이너가 빈 객체를 초기화 할 때는 org.springframework.beans.factory.InitializingBean 인터페이스에 정의됨
// afterproperties 메서드를 활용해서 빈 객체를 초기화 
// 개발자가 빈 객체의 초기화에 직접 과녕하고 싶을 떄 org.springframework.beans.factory.InitializingBean 인터페이스
// 컨테이너를 만들면 됨
// 스플이 컴테이너가 빈 객체를 소멸할 떄는 org.springframework.beans.factory.DisposableBean 인터페이스에 정의됨
// destroy 메서드를 활용해서 빈 객체를 소멸
// 직접 초기화와 소멸에 관여할 일이 많이 없을 것같지만 의외로 빈번하게 발생함

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Context.class);
		
		// 이 프로그램에서 실제로 사용하는 메서드는 두 개뿌 ㄴ이지만
		// 초기화에 관여하는 메서드를 오버라이딩 해뒀고
		// 소멸에 관여하는 메서드를 오버라이딩 해뒀으므로
		// 우리가 오버리이딩한 메서드가 호출되 초기화. 소멸에 우리가 직접 관여할 수 있음
		
		// 빈 객체는 기본적으로 싱글톤(singleton) 범위(scope)를 갖는다.
		Client client = ctx.getBean(Client.class);
		client.setHost("127.0.0.1");
		client.send();
		
		try {
			// 초기화, 소멸에 관여하는 메서드는 public접근 제어자이기 때문에 (컨테이너에 접근할 수 있도록 하기 위해 private사용 못함)
			// 직접 호출할 수 있음
			// 그러나 직접 호출하면 안됨!
			// 초기화와 소멸에 관여하는 메서드는 라이프사이클에 의해서 자동으로 호출되도록 해야함
			client.afterPropertiesSet();
			client.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Client2 client2 =ctx.getBean(Client2.class);
		client2.send();
		
		ctx.close();
	}

}

