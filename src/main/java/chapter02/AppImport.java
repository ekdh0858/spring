package chapter02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//설정 클래스가 두개 (AppImport, AppConf2)로 나눠져 있지만
// @Import 애노테이션을 사용해서
// AppImport 설정 클래스 하나로 묶었음

@Configuration
@Import({AppConf2.class})
public class AppImport {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}
