package chapter02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//우리가 지금까지는 애노테이션을 클래스와 메서드에만 붙였음
// 애노테이션은 멤버 변수에도 붙일 수 있음

// 의존 객체가 있는( 의존 주입이 필요한) 빈 설정 클래스
@Configuration
public class AppConf2 {
	// @Autowired -> 자동 의존 주입을 위한 애노테이션
	// 스프링 설정 클래스의 멤버 변수에 @Autowired 애노테이션이 븥어있으면
	// 해당 멤버 변수 타입의 빈을 찾아서 멤버 변수에 저장
	@Autowired
	private MemberDao memberDao;
	// AppConf2에는 MemberDao, MemberPrinter 타입의 빈이 없지만
	// AppConf2와 연결해서 컨테이너를 만들 AppConf1에는 두 타입의 빈이 있음
	// 스프링이 AppConf1에서 두 타입의 빈을 가져와서 멤버 변수에 저장
	
	// 객체를 
	
//	@Autowired
	private MemberPrinter memberPrinter = new MemberPrinter();
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao);
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
		
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao,memberPrinter);
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		// 의존 주입이 필요한 멤버 변수에 @AAutowired 에노테이션을 붙였으므로
		// 직접 의존 주입을 해줄 필요가 없음
//		infoPrinter.setMemberDao(memberDao);
//		infoPrinter.setPrinter(memberPrinter);
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(1);
		versionPrinter.setMinorVersion(4);
		return versionPrinter;
	}
	
	@Bean
	public VersionPrinter OldVersionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(1);
		versionPrinter.setMinorVersion(4);
		return versionPrinter;
	}
}
