package chapter06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chapter06.config.AppCtx;

public class Main3 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		// getBean 메서드로 이름이 calculator인 빈 객체를 가져올 때는
		// 빈 객체를 등록할 때 지정했던 타입 (Calculator)을 사용해서 가져와야함
		// 실제 cal 객체에 들어있는 인스턴스 타입은 ImpCalculator1 이어야할 것
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		
		
		// factorial 메서드를 호출하면 ImpCalculator1 클래스에 정의해둔 factorial 메서드가 동작
		// 우리가 스프링 컨테이너를 만들때 사용한 설정 클래스 (AppCtx) 에 Aspect를 적용했기 때문에
		// 실제로는 그렇지 않음
		// 실제로는 Aspect가 적용되서 ExecTimeAspect 클래스 내 measure 메서드가 동작
		// measure메서드의 proceed() 메서드를 통해서 ImpCalculator1 클래스에 정의해둔 factorial 메서드가 동작
		cal.factorial(5);
		
		// 객체명.getClass() -> 클래스 정보를 반환해줌
		// 객체명.getClass().getName() -> 클래스명을 반환
		// cal 객체가 갖고 있는 실제 인스턴스는 스프링이 프록시 디자인 패턴을 적용한 완전히 새로운 인스턴스를 갖고 있음
		// 그 새로운 인스턴스의 타입 ( 클래스 ) 는 Proxy~~ 임
		System.out.println(cal.getClass().getName());
		
		ctx.close();
	}

}
