package chaper01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 스프링의 핵심 기능 중 하나는 객체를 생성하고 초기화하는 것
// 이와 관련된 기능은 ApplicationContext 인터페이스에 정의되어있음
// 우리가 사용한 AnnotationConfigApplicationContext 클래스는 
// 이 인터페이스에 알맞게 구현한 클래스 중 하나
// AnnotationConfigApplicationContext클래스는 자바 클래스에서 정보를 읽어와 객체를 생성하고
// 초기화하는 클래스
// XML파일에서 정보를 읽어와 객체를 생성하고 초기화하는 클래스도 있음(GenericXmlApplicationContext)
// Groovy 설정 코드를 이용해서 객체 생성하고 초기화하는 클래스도 있음(GenericGroovyApplicationContext)
// Bean 팩토리 -> 검색과 관련된 팩토리(싱글톤인지 프로토타입인지 확인도 가능)
// ApplicationContext(또는 BeanFactory)는 빈 객체를 생성, 초기화, 보관, 제거하는 등
// 빈 객체를 관리하고 있어서 ApplicationContext를 컨테이너(Container) 라고도 부름

public class Main {

	public static void main(String[] args) {
		// AppContext에 정의해둔 @Bean 설정 정보를 읽어와
		// Greeter 타입의 객체를 생성하고 초기화함
		// 생성된 객체는 ctx 내부에 보관되어 있음
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		// getBean 메서드는 AnnotationConfigApplicationContext가 자바 설정을 읽어와
		// 빈 객체를 검색할 때 사용
		// 첫 번째 인자 - @Bean 애노테이션이 달린 메서드의 이름
		// 두 번째 인자 - 검색할 빈 객체의 타입
		Greeter g = ctx.getBean("greeter",Greeter.class);
		
		// 가져온 빈 객체를 사용해서 greet 메서드를 호출
		String msg = g.greet("스프링");
		
		System.out.println(msg);
		
		ctx.close();
	}

}
