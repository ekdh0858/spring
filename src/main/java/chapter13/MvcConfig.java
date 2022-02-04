package chapter13;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/",".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}
	
	// message 패키지 안에 만들어둔 메세지 파일인 label.properties 를 불러와
	// 해당 파일 내 메세지들을 message.label 이라는 이름에 저장
	// message.label -> message 패키지 안에 있는 label.properties 파일을 불러와라
	// label.properties 파일 내 문자 인코딩을 UTF-8로 사용했으므로 
	// 불러온 문자열의 인코딩을 UTF-8로 설정
	
	// 주의사항!
	// 메세지 파일을 불러오는 빈의 이름은 반드시 messageSource 여야함
	@Bean
	public MessageSource messageSource() {
		// messageSource 인터페이스를 구현한 클래스
		// code 매개변수로 전달받은 값과 일치하는 이름을 가진 프로퍼티의 값을 반환하는 역활
		// ResourceBundleMessageSource 클래스는 자바의 리소스 번들(ResourceBundle)을 사용하기 때문에 클래스명에 
		// 이러한 이름이 붙은 것이고 자바의 리소스 번들은 클래스 Path에 위치해야함
		// 클래스 Path에는 src/main/resources 가 포함되어있기 때문에 그 위치에 프로퍼티 파일을 둔 것
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		
		ms.setBasenames("message.label");
		ms.setDefaultEncoding("UTF-8");
		
		return ms;
	}

	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 테스트 인자로 사용한 경로는 Ant하는 곳에서 사용하는 경로 패턴
		registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/edit/**","/logout")
		.excludePathPatterns("/edit/help/**");
		// 모든 /edit/**이러한 패턴을 갖는 요청이 들어오면 이 인터셉터(authCheckInterceptor)가 동작한다.
		// excludePathPatterns을 사용해서 특정 경로의 요청에서는 적용을 안시킬수도 있음(특정 경로 제외)
	}

	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}
	
//	@Override
//	public Validator getValidator() {
//		return new RegisterRequestValidator();
//	}

}
