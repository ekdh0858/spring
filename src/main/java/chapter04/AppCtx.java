package chapter04;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//ComponentScan은 해당 패키지 내 모든 클래스로 조사해서
//@Component 애놑테이션이 달린 클래스를 스프링 컨테이너에 빈으로 등록

// @Component 애노테이션이 달려있기는 하지만 특정 설정 클래스에서는 
// 해당 클래스를 컴포넌트 스캔 대상에서 제외를 할 수 있는 방법이 있어야함
// 이때 사용할 수 있는 속성이 excludeFilters
// excludeFilters를 사용하면 지정한 클래스를 컴포넌트 스캔 대상에서 제외할 수 있음
// type=FilterType.REGEX -> 제외 대상 지정을 정규표현식으로 지정하겠다


@Configuration
 @ComponentScan(basePackages = {"chapter04"})
//@ComponentScan(
//		basePackages = {"chapter04","etc"},
//		excludeFilters = @Filter(type=FilterType.REGEX, pattern="chapter04\\...*Dao")
//		excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MemberDao.class)
//		)

public class AppCtx {
	// 수동 등록한 빈 객체가 있고
	// @Component 애노체이션을 단 빈의 객체가 있다
	// 그럴 때 두 빈 객체의 이름이 같으면
	// 수동 등록한 빈 객체만 살아남음
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	@Bean
	public MemberPrinter memberPrinter2() {
		return new MemberPrinter();
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
