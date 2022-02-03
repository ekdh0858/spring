package chapter12;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
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
	public String form(LoginCommand loginCommand, @CookieValue(value="remember", required=false) Cookie cookie) {
		// 처음 접근했을 때는 쿠키에 저장된 값이 없을 수도 있으니 필수 항목은 아님
		if(cookie != null) { // 이메일이 저장된 쿠키가 있나요? 를 체크함
			//이메일 입력란에 쿠키에 저장된 이메일을 보여주는 처리
			
		}
		
		return "/login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand,
			Errors errors,
			HttpSession session, 
			HttpServletResponse response) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "/login/loginform";
		}
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			// 로그인에 실패했을때 -> 세션에 저장할 코드임			
			session.setAttribute("authInfo", authInfo);
			if(loginCommand.isRememberEmail()) {
				//로그인 커맨드에 들어있는 이메일을 기억 유무 체크
				Cookie rememberCookie = new Cookie("remember", loginCommand.getEmail());
				rememberCookie.setPath("/");
				rememberCookie.setMaxAge(60 * 60*24*30); // 30일 유지함
				//쿠키에 이메일을 저장
				
				response.addCookie(rememberCookie);
			// 쿠키를 담아서 전달해줌
			}
			// 어스 인포를 세션에 저장을 함
			
			return "/login/loginSuccess";
		}catch(WrongIdPasswordException e) {
			errors.reject("isPasswordNotMatching");
			
			return "/login/loginForm";
		}
	}
	
}
