package chapter11;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String handleStep2(
			@RequestParam(value="agree", defaultValue = "false")boolean agree,
			Model model) {
		
		if(!agree) {
			// register/step1.jsp 뷰를 전달
			return "register/step1";
		}
		
		// step2 .jsp 뷰에서는 registerRequest 라는 이름의 커맨드 객체를 사용하는데
		// 컨트롤러에서는 뷰로 그러한 이름의 커맨드 객체를 전달해주지 않고 있음
		// 그래서 뷰에서 예외가 발생함
		// 따라서 예외가 발생하지 않게 커맨드 객체를 전달해줘야함 -> 빈 커맨드 객체를 전달해줘야함
		model.addAttribute("registerRequest",new RegisterRequest());
		
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
	
	// 요청 처리 메서드에 커맨드 객체가 있다면 스프링은 스스로 뷰로 커맨드 객체를 전달함
	// 그때 전달할 커맨드 객체의 이름은 커맨드 객체의 데이터 타입을 사용
	// 커맨드 객체의 데이터 타입의 이름을 직접 지정하고 싶다면 @ModelAttribute 애노테이션을 사용하면 됨
	@PostMapping("/step3")
//	public String handleStep3(@ModelAttribute("formData") RegisterRequest regreq) {
	public String handleStep3(@Valid RegisterRequest regreq, Errors errors) {
		// vaildate 메서드는 커맨드 객체의 멤버 변수를 검증하는 메서그
		// 상황에 따라서는 커맨드 객체 자체가 잘못욌을 경우도 있음
		// 그럴 경우에는 rejectValue 메서드가 아니라
		// reject 메석드를 활용해서 에러 코드를 기록함
		// reject 메서드는 특정 멤버 변수가 아니라 객체 자체가 잘못됬다를 기록하는 메서드 이므로
		// reject 메서드로 기록한 에러 코드를 글로벌 에러 코드라고 함
		
		
		// 스프링이 아니라 우리가 직접 검증하는 코드를 작성해보는 것임
//		new RegisterRequestValidator().validate(regreq, errors);
		
		if(errors.hasErrors()) {
			return "register/step2";
		}
		// 에러스라는 값에 에러가 있을때 2단계로감. 하나라도 에러가 있을시 true되면서
		
		
		// 클라이언트가 보낸 데이터를 꺼내서 
		// 검증하는 과정은 생략하고 
		// RequesterRequest 타입의 객체에 저장하세요
		try {
			memberRegSvc.regist(regreq);
			return "register/step3";
		}catch(DuplicateMemberException e) {
			// 회원가입 시 이미 존재하는 이메일을 사용해서 가입을 하면
			// 곧바로 다시 회원 정보를 입력하는 뷰가 보임
			// 그러나 회원 정보를 입력하는 뷰가 보일 때 빈 화면이 보임
			// 사용자가 입력했던 이메일, 이름은 그대로 유지한채로 보여주고 싶다면
			// 커맨드 객체를 활용하면 됨
			
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
	// 단일 컨트롤러에서 사용할 validator 임 
	// 스텝1과 스테2에서는 발리데이터 클래스가 없는데 이 클래스가 호출되서 생성함. 
	// -> 컨트롤러안에 있는 메서드가 실행될때마다 계속 호출됨
//	@InitBinder // 이 컨트롤러에서 사용할 발리데이터가됨
//	protected void initBiinder(WebDataBinder binder) {
//		binder.setValidator(new RegisterRequestValidator());
//	}
}
