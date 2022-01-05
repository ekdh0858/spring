package chapter08;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Controller 애노테이션을 적용한 객체는 DispatcherServlet입장에서 보면 한 종류의 핸들러 객체임
// DispatcherServlet은 웹 브라우저의 요청을 처리할 핸들러 객체를 찾기 위해서 handlerMapping을 사용하고 
// 핸들러를 실행하기 위해서 HandlerAdapter를 사용함

// DispatcherServlet은 스프링 컨테이너에서 HandlerMapping과 HandlerAdapter 타입의 빈을 사용함
// 핸들러에 알맞는 HandlerMapping과 HandlerAdapter 빈을 등록해야함

// @EnableWebMvc -> 스프링 MVC 활성화
// WebMvcConfigurer 인터페이스 -> 스프링 MVC의 설정을 조정할 때 사용
@Configuration
// 우리 눈에는 안보이지만 @EnableWebMvc 애노테이션을 통해서 handlerMapping과 handlerAdapter 빈 객체를 등록하는 코드가 만들어짐
// Handlermapping 의 정확한 타입은 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
// handlerAdapter의 정확한 타입은 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter

// RequestMappingHandlerMapping는 @Controller 애노테이션이 적용된 객체의 요청 매핑 애노테이션(@Getmapping)을 이용해 웹 브라우저의 요청을 
// RequestMappingHandlerAdapter는 컨트롤러의 메서드를 실행하고 그 결과를 ModelAndView 객체로 반환해서 DispatcherServlet에게 반환

// 또, @Controller 애노테이션을 붙인 컨트롤러를 위한 설정을 생성함
// 또, WebMvcConfigurer 타입의 빈을 사용해서 MVC 설정을 추가로 생성
// 또, MVC설정을 추가로 생성할 때는 WebmvcConfigurer 인터페이스에 선언된 메서드를 호출해서 생성
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{
	
		// DefaultServlet과 관련된 설정
		// DispatcherServlet의 매핑 경로를 / 로 주었을 떄, JSP/HTML/Css 등 웹 요소들을 올바르게 처리하기 위한 설정
		// DispatcherServlet -> 스프링 Mvc가 웹 요청을 처리하려면 반드시 필요한 요소
		// 스프링 MVC가 웹 요청을 처리할 때DispatcherServlet을 통해서 웹 요청을 받음
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// ViewResolver와 관련된 설정
	// 요청 결과를 보여주기 위한 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/",".jsp");
	}
	
	
	
}
