package chapter06.aspecct;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// @Aspect 애노테이션을 적용한 클래스는 Advice, Pointcut을 반드시 함께 구현해야함
@Aspect
public class ExecTimeAspect {
	
	// 공통 기능을 적용할 대상 설정
	// chapter06 패키지에 위치한 클래스의 접근 제어자가 public인 메서드를 pointcut으로 설정한다. 
	@Pointcut("execution(public * chapter06.*.*(..))")
	public void publicTarget() {
		
	}
	
	// 이 클래스는 Aspect인데 Around Advice에서 사용할 Aspect
	// Around Advice 의미 / Around 애노테이션의 값으로 publicTarget() 메서드를 넣음
	// 이는 publicTarget()메서드 위에 적어둔 @pointcut 애노테이션의 규칙에 맞는 메서드에 공통 기능(measure)을 적용하겠다.
	// chapter06 패키지에 속한 빈 객체의 public 메서드에 @Around 애노테이션이 붙은 measure() 메서드를 적용함	
	@Around("publicTarget()")
	// Around Advice에서 사용할 핵심 기능 메서드는 매개젼수로 정달 받은 ProceedingJoinPoint의 proceed() 메서드만 호출하면 됨
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.nanoTime();
		
		try {
			// joinPoint.proceed() 메서드를 사용해서 실제 대상 객체의 메서드를 호출
			// 이 메서드를 호출하면 대상 객체의 메서드가 실행되므로 이 코드 이전, 이후에 공통 기능을 위한 코드를 위치시키면 됨
			Object result = joinPoint.proceed();
			return result;	
		} finally {
			long finish = System.nanoTime();
			
			// getSignature() -> 호출한 메서드의 시그니처
			// 시그니처 -> 메서드 이름과 매개변수를 합쳐서 시그니처 라고 함
			// 메서드 이름이 다르거나 매개변수 타입, 개수가 다르면 시그니처가 다르다고 얘기함
			// Signature 인터페이스에 정의된 getName(), toLongString(), toshortString()
			// getName() -> 호출되는 메서드의 이름을 반환
			// toLongString() -> 호출되는 메서드를 완전하게 표현한 문장을 반환( 메서드의 반환타입, 매개변수 타입을 모두 표시)
			// toShortString() => 호출되는 메서드를 축약해서 표현한 문장을 반환( 메서드의 이름만 표시해 봔환)
			Signature sig = joinPoint.getSignature();
			// getTarget() -> 대상 객체를 반환하는 메서드
			String simpleName = joinPoint.getTarget().getClass().getSimpleName();
			String name = sig.getName();
			// getArgs() -> 매개변수 목록을 반환하는 메서드
			String args = Arrays.toString(joinPoint.getArgs());
			
			long duration = finish - start;
			
			System.out.println(simpleName + "." + name +"("+args+") 실행시간 =>"+duration);
		}
		
		
	}
}
