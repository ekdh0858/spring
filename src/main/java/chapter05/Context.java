package chapter05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// 일반적으로 ComponentScan을 사용해서 빈 객체를 등록하고
// 수동으로 등록한 빈 객체가 등록됨
// 스프링의 버전이나 자바의 버전에 따라서 순서가 달라질 순 있음

@Configuration
@ComponentScan(basePackages = "chapter05")
public class Context {
	// initMethod ->초기황[ 관여할 메서드를 직접 지정
	// destroyMethod -> 소멸에 관여할 메서드를 직접 지정
	// 특징 -> 인자를 전달해 줄 수 없다 / 초기화, 소멸에 관여하는 메서드를 정의할 때 매개변수가 잇으면 안됨
	@Bean(initMethod="connect", destroyMethod = "close")
	@Scope("singleton")
	public Client2 client2() {
		Client2 client2 = new Client2();
		client2.setHost("localhost");
		
		return client2;
		
	}

}
