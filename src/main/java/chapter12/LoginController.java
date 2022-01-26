package chapter12;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private AuthService authService;
	
	public void setAuthService(AuthService authService){
		this.authService = authService;
	}
	
	@GetMapping
	public String form(LoginCommand loginCommand) {
		return "/login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand,Errors errors,HttpSession session) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "/login/loginform";
		}
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			// 로그인에 실패했을때 -> 세션에 저장할 코드임			
			session.setAttribute("authInfo", authInfo);
			// 어스 인포를 세션에 저장을 함
			
			
			return "/login/loginSuccess";
		}catch(WrongIdPasswordException e) {
			errors.reject("isPasswordNotMatching");
			
			return "/login/loginForm";
		}
	}
	
}
