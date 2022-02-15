package chapter15;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		// MappingJackson2HttpMessageConverter 객체를 새롭게 재정의 하는 메서드를 오버라이딩 한것임.
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
				.json()
//				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				// 날짜를 타임스탬프 형식으로 출력되는 데이터를 Disable하라는 속성을 추가해줌. -> 차선책으로 ISO-8601이라는 형태로 변환 해줌
				.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(dft))// -> 보낼때
				.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(dft)) // -> 받을 때 
				// LocalDateTime 형식을 우리가 지정한 여형식으로 변환하고 싶을 때 사용함. 위에는 차선책을 쓰고지금은 우리가 원하는 방식으로 변환(dft형식)
				.build();
		// MappingJackson2HttpMessageConverter객체를 새롭게 재정의 한것을 스프링이 사용하도록 하려면
		// 아래와 같이 0번 인덱스(가장 앞)에 추가를 해줘야함.
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
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
