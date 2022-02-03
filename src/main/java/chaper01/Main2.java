package chaper01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		// Greeter 타입의 빈 객체를 g1에 저장
		Greeter g1 = ctx.getBean("greeter",Greeter.class);
		
		// Greeter 타입의 빈 객체를 g2에 저장
		Greeter g2 = ctx.getBean("greeter",Greeter.class);		
//		Greeter g2 = ctx.getBean("greeter1",Greeter.class);
		
		//스프링은 기본적으로 빈 객체를 싱글톤 다지인 패턴을 적용해서 만듦
		System.out.println("(g1 == g2) = "+(g1==g2));
		ctx.close();
		
	}

}
