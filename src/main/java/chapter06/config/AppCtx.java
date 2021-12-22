package chapter06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import chapter06.Calculator;
import chapter06.ImpCalculator1;
import chapter06.aspecct.ExecTimeAspect;

// @EnableAspectJAutoProxy -> @Aspect 애노테이션이 붙ㅌ은 공통 기능을
// 핵심 기능에 적용하기 위해서 설정 클래스에 필요한 애노테이션
// 이 애노테이션을 추가해야 스프링 컨테이너가 이 애노테이션을 보고 공통 기능과 핵심 기능을 연결
@Configuration
@EnableAspectJAutoProxy
public class AppCtx {
	@Bean
	public ExecTimeAspect execTimeAspect() {
		return new ExecTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new ImpCalculator1();
	}
	
}
