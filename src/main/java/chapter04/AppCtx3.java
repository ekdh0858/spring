package chapter04;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppCtx3 {
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

	@Bean
	@Qualifier("printer1")
	public MemberPrinter2 memberPrinter1() {
		return new MemberPrinter2();
	}

	@Bean
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
//	@Bean	
//	public MemberListPrinter listPrinter() {		
//		return new MemberListPrinter();
//	}
	
	@Bean
	public MemberInfoPrinter2 infoPrinter() {
		MemberInfoPrinter2 infoPrinter = new MemberInfoPrinter2();
		
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
