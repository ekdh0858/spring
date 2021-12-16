package chapter02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 의존 객체가 없는 빈 설정 클래스
// memberDao, memberPrinter 빈 객체는
// 직접 사용하지 않고
// appConf2에서 설정한 빈 객체의 의존 객체 역활을 함
// 주입할 객체가 꼭 스프링 빈이여야하는건 아님
// 객체를 스프링 빈으로 등록할 때와 등록하지 않을 떄의 차이는 스프링 컨테이너가 객체를 관리하는지 여부
// 스프링 컨테이너는 DI, 라이프사이클 관리 등 단순히 객체를 생성하기만 하지는 않고 객체 관리를 위한 다양한 기능을 제공
// 단, 빈으로 등록한 객체만 스프링 컨테이너가 관리를 해줌

@Configuration
public class AppConf1 {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
//	@Bean
//	public MemberPrinter memberPrinter() {
//		return new MemberPrinter();
//	}
}
