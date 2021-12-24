package chapter06.aspecct;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

// 하나의 핵심 기능에 여러 Aspect를 붙일 수 있음
// chapter06 패키지 안에 있는 메서드 중에서 매개변수가 long타입 하나인 메서드는
// ExecTimeAspect, CacheAspect 모두 적용됨

@Aspect
@Order(2) // -> 순서 지정해주는것 , 근데 사실 순서가 뒤죽박죽이여도 상관이없어야함
public class CacheAspect {
	// 핵심 기능읭 결과값을 임시로 저장할 저장소
	private Map<Long,Object> cache = new HashMap<>();
	
	// long타입 매개변수 하나를 갖고 있는 메서드
//	@Pointcut("execution(public * chapter06.*.*(long))")
//	public void cacheTarget() {
//		
//	}
//	
//	@Around("cacheTarget()")
	// @Pointcut 애노테이션 대신에 @Pointcut 애노테이션 없이 @Around애노테이션에 Execution명시자를 직접 지정할 수도 있음
//	@Around("execution(public * chapter06.*.*(long))")
	// @Pointcut이 동일한 Aspect가 많이 있을 때는 @Pointcut을 재사용할 수 있음
	// @Ponintcut을 재사용할 때는 재사용 하고자 하는 클래스의 @Pointcut 없이 @Around 애노테이션에 재사용할 @Pointcut을 갖고 있는
	// 메서드 이름의 전체 경로를 써주면 됨
	@Around("chapter06.aspect.ExecTimeAspect.publicTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		// getArgs() -> 핵심 기능을 실행하기 위해 전달한 인자들을 반환
		long num = (long) joinPoint.getArgs()[0];
		
		// num 변수에 들어있는 값을 키로 사용해서 
		// cache에 들어있는 키 중 num이 존재한다면 num키의 값을 반환
		if(cache.containsKey(num)) {
			long result = (long) cache.get(num);
			
			System.out.println("CacheAspect : Cache에서" + num +"ket의 값" +result+"찾음");
			return cache.get(num);
		}
		
		// cache에 들어있는 키 중 num이 존재하지 않는다면
		// 프록시 대상 객체의 메서드 (핵심 기능) 실행
		Object result = joinPoint.proceed();
		// num을 키로 사용해서 핵심 기능이 반환해준 값을 cache에 저장
		cache.put(num, result);
		
		System.out.println("CacheAspect : Cache에 key="+num+",value="+result+"추가");
		return result;
	}
	
}
