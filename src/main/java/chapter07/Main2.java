package chapter07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		// 로그 : 프로그램 또는 서버에서 발생하는 모든 것
		// 로깅 : 로그를 기록하는 행위
		// 로깅 모듈을 사용해서 트랜잭션 관련 로그를 남기도록 할 예정
		ChangePasswordService cps = ctx.getBean(ChangePasswordService.class);
		
		try {
			// 우리는 애노테이션만 달았는데 커밋과 롤백이 알아서 됨
			// 커밋과 롤백은 누가 어떻게 하는걸까?
			// 공통으로 적용되는 기능을 구현하는 방법으로 AOP 설명
			// 트랜잭션도 Aspect(공통 기능) 중 하나
			// 스프링에서는 AOP가 프록시 디자인 패턴을 활용해서 적용되므로 트랜잭션 처리도 츠록시흫 통해서 이루어짐
			// @Transactional 애노테이션을 적용하기 위해 @EnableTransactionManagement 애노테이션을 사용하면
			// 스프링은 @Transactional 애노테이션이 적용된 빈 객체를 찾아서 알맞는 프록시 객체를 생성 
			cps.ChangePasswordService("1228110605@est.com", "1212", "1111");
		}catch(MemberNotFoundException e) {
			System.out.println("이메일과 일치하는 회원 정보가 없습니다.");
		}catch(WrongIdPasswordException e) {
			System.out.println("암호가 올바르지 않습니다.");
		}
		ctx.close();
	}

}
