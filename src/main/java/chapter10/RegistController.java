package chapter10;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @GetMapping, @PostMapping 등 특정 요청 방식을 처리할 수 있는 애노테이션은 
// 스프링 4.3부터 추가된 것
// 스프링 4.3미만 버전에서는 @RequestMapping만 사용할 수 있음
// @GetMapping("URL") -> @RequestMapping(value="/step2",method=RequestMapping.GET)
// @PostMapping("URL") -> @RequestMapping(value="/step2",method=RequestMapping.POST)

// 요청 매핑 애노테이션은 하나의 컨트롤러 안에 여러 개가 있을 수 있음
@Controller
// 요청 매핑 애노테이션에서 공통 URL은 클래스 부분으로 따로 분리할 수 있음
@RequestMapping("/register") // register로 시작하는 모든 요청은 이 컨트롤러로 온다.
public class RegistController {
	private MemberRegisterService memberRegSvc;
	
	public RegistController(MemberRegisterService memberRegSvc) {
		this.memberRegSvc = memberRegSvc;
	}
	
	
	// 약관 동의 화면 요청 처리
	@GetMapping("/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	// @PostMapping() -> POST방식의 요청만 처리하고 싶을 때는 이 애노테이션을 사용
	// @RequestParam(value="agree", defaultValue = "false") -> 이름이 agree인 파라미터 값을 매개변수에 저장/ 이름이 agree인 파라미터가 없을 때는 defaultValue의 값을 저장
	// 주의할 점! defaultValue는 문자열임! 
	// 회원 정보 입력 화면 요청 처리
	@PostMapping("/step2")
	public String handleStep2(@RequestParam(value="agree", defaultValue = "false")boolean agree) {
		
		if(!agree) {
			// register/step1.jsp 뷰를 전달
			return "register/step1";
		}
		
		return "register/step2";
	}
	@GetMapping("/step2")
	public String handleStep2Redirect() {
		// redirect 를 사용하면 URL 자체를 바꾼다.
		// redirect를 할 때는 프로토콜, 호스트, 프로젝트 명을 생략하고 URL을 입력함
		// redirect를 할 때 / 부터가 아니라 /를 생략하고 경로를 입력하면 상대경로로 이동함
		return "redirect:/register/step1";
	}
	
//	
//	// 하나의 url에 서로 다른 Method 를 처리하도록 매핑할 수 있음
//	@GetMapping("/step2")
//	public String handlerStep2_2() {
//	return "register/step2_2";
//	}
	
	// RequestMapping() -> 요청 방식에 상관없이 모든 요청을 처리할 수 있는 애노테이션
	// 가입 처리 결과 화면 요청 처리
	// 클라이언트가 전달해주는 데이터가 많을 경우 HttpServletRequest 또는 @Requestparam 애노테이션으로 
	// 꺼내서 쓰기에는 코드가 너무 길어지는 단점이 잇음
	// 그래서 스프링에서는 커맨드(Command) 객체를 제공
	// 예를 들어 이름이 email인 요청 파라미터 값을 커맨드 객체에 저장하려면
	// 커맨드 객체에 setEmail(세터)가 있기만 하면됨
	@PostMapping("/step3")
	public String handleStep3(RegisterRequest regreq) {
		
		// 클라이언트가 보낸 데이터를 꺼내서 
		// 검증하는 과정은 생략하고 
		// RequesterRequest 타입의 객체에 저장하세요
		try {
			memberRegSvc.regist(regreq);
			return "register/step3";
		}catch(DuplicateMemberException e) {
			return "return/step2";
		}
	}
}
