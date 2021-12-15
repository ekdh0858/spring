package chapter02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 조립기(Assembler) 대신 스프링 DI를 사용해서
// 객체를 생성하고 의존 주입(DI)를 한 것 
// 스프링 DI를 사용하려면 애노테이션, XML, Groovy를 사용해서 DI 설정을 해야함
// 여러 방법 중 애노테이션을 사용해 DI 설정을 한 것

// 스프링은 설정 클래스(AppCtx)를 그대로 사용하지 않음
// 설정 클래스를 상속한 새로운 설정 클래스를 만들어서 사용

// @Configuration -> 특정 클래스를 스프링 설정 클래스로 만들어주는 애노테이션
@Configuration
public class AppCtx {
	
	// @Bean ->  이 메서드가 생성한 객체를 스프링 빈으로 등록하기 위한 애노테이션
	// MemberDao타입의 빈 객체가 memberDao 라는 이름으로 스프링에 등록
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	//MemberRegisterService 타입의 빈 객체가 memberRegSvc 이름으로 스프링에 등록	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	// ChangePasswordService 타입의 빈 객체가 changePwdSvc 이름으로 스프링에 등록
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService cps = new ChangePasswordService();
		cps.setMemberDao(memberDao());
		
		return cps;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(),memberPrinter());
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		
		return infoPrinter;
	}
	
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(1);
		versionPrinter.setMinorVersion(4);
		
		return versionPrinter;
	}
}
