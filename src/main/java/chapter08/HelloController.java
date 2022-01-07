package chapter08;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// http://localhost:8080/springf5/hello?name=guest
@Controller
public class HelloController {
	
	// RequestMappinghandlerAdapter는 컨트롤러의 메서드가 반환하는 값이 String이면 해당 값을 뷰 이름으로 직는 modelAndView 객체를 생성
	// 이때, 컨트롤러의 메서등서 사용한 model타입의 매개변수에 보관된 값도 modelAndView에 담아서 전달
	
	// @GetMapping -> 이 메서드가 처리할 경로를 지정(Get방식으로 요청한 것만 처리)
	// Model 타입의 매개변수 -> 컨트롤러의 데이터를 뷰로 전달할 때 사용(객체에 저장해서 전달해야하니까)
	// @RequestParam 애노테이션이 붙은 매개변수 -> 이름이 (value값인)name인 요청 파라미터의 값을 이 매개변수에 저장(클라이언트에서 전달함)
	// required 속성 : 필수 여부 지정
	@GetMapping("/hello")
	public String hello(Model model, 
			@RequestParam(value = "name",required = false) String name) {
		
		// 뷰에 전달할 데이터를 저장
		model.addAttribute("greeting", name+"님 안녕하세요~!");
		
		// 컨트롤러의 결과를 보여줄 뷰를 지정
		// 결과를 보여줄 뷰 파일의 이름은 hello.jsp
		// 뷰 파일의 경로는 루트 디렉토리(/)임
		return "hello";
	}
}
