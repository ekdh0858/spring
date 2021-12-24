package chapter06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import chapter06.Calculator;
import chapter06.ImpCalculator1;
import chapter06.aspecct.CacheAspect;
import chapter06.aspecct.ExecTimeAspect;

// @EnableAspectJAutoProxy -> @Aspect 애노테이션이 붙ㅌ은 공통 기능을
// 핵심 기능에 적용하기 위해서 설정 클래스에 필요한 애노테이션
// 이 애노테이션을 추가해야 스프링 컨테이너가 이 애노테이션을 보고 공통 기능과 핵심 기능을 연결
@Configuration
// 인터페이스를 기반으로 프록시 객체를 만드는게 아니라
// 클래스를 기반으로 프록시 객체를 만들고자 할 때는
// @EnableAspectJAutoProxy 애노테이션에 proxyTargetclass = true 속성을 주면 됨

@EnableAspectJAutoProxy //(proxyTargetClass = true)
public class AppCtx {
	
	@Bean
	public CacheAspect cacheAspect() {
		return new CacheAspect();
	}
	
	@Bean
	public ExecTimeAspect execTimeAspect() {
		return new ExecTimeAspect();
	}
	
	// calculator 이름의 빈 객체 타입은 Calculator 타입
	// 이 빈 객체의 실제 인스턴스 타입은 Impcalculator1 타입
	@Bean
	public Calculator calculator() {
		return new ImpCalculator1();
	}
	
}
