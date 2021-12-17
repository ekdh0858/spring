package chapter04;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppCtx2 {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService cps = new ChangePasswordService();
		
		return cps;
	}
	// memberPrinter1 빈과 memberPrinter2 빈은
	// 서로 데이터 타입이 다름
	// 한정자를 배분 이유 -> listPrinter, infoPrinter 빈을 등록할 때 
	// 자동 주입할 탕비의 빈이 두 개여서 예외가 발생
	// 이 문제를 해결하기 위해서 한정자를 배우고
	// 스프링이 자동 의존 주입을 할 때 한정자를 사용해서 의존 주입할 빈을 선택하도록 했음
	@Bean
	@Qualifier("printer1")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	// memberprinter2 이 빈은 MemberSummaryPrinter 타입이고
	// MemberSummaryPrinter 클래스는 MemberPrinter 클래스를 상속받았기 때문에
	// MemberPrinter 타입도 되고 MemberSummaryPrinter 타입도 될 수 있음
	// 그래서 같은 타입이다 라는 예외가 발생하고 있음
	@Bean
//	@Qualifier("printer2")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	
	public MemberListPrinter listPrinter() {		
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(1);
		versionPrinter.setMinorVersion(4);
		
		return versionPrinter;
	}
}
