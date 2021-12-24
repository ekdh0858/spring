package chapter06.pointcut;

import org.aspectj.lang.annotation.Pointcut;

//Pointcut을 재사용하는 경우가 많으면
// 다음과 같이 Pointcut만 갖고 있는 클래스를 만들어도 됨 
// Pointcut만 갖고 있는 클래스는 @Aspect 애노테이션이 붙은 클래스가 아니므로
// 빈 객체로 등록하지 않아도 됨
public class CommonpPointcut {
	@Pointcut("execution(public * chapter06.*.*(..))")
	public void commonTarget() {
		
	}
	
	@Pointcut("execution(public * chapter06.*.*(long))")
	public void commonTarget2() {
		
	}
}
