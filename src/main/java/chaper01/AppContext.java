package chaper01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration -> 이 클래스를 스프링 설정 클래스로 지정하는 애노테이션
@Configuration
public class AppContext {
	
	//스프링은 객체를 생성하고 초기화하는 기능을 제공
	// 그 객체를 생성하고 초기화하는 설정이 12~19번째 줄에 담겨있는 것	
	@Bean
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setFormat("%s,드디어 시작!");
		return g;
	}
	
	@Bean
	public Greeter greeter1() {
		Greeter g =new Greeter();
		g.setFormat("%s,안녕하세요!");
		return g;
	}
	// 스프링이 생성하는 객체를 빈(Bean)이라고 부름
	// 빈 객체에 대한 정보를 담고 있는 메서드가 greeter메서드
	// 위 메서드는 @Bean 에노테이션이 붙어있음
	//메서드에 @Bean 에노테이션이 붙어있으면 이 메서드가 반환한 객체를 스프링이 관리하는 빈 객체로 등록
	
	// 빈 객체를 여러개 등록할 수 있음
	// 빈 객체간에 서로 구별할 때는 @Bean 에노테이션이 붙은 메서드의 이름을 사용
	// @Bean 에노테이션이 붙은 메서드의 이름이 빈 객체 식별자가 되는것
}
