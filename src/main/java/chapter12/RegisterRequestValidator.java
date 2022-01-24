package chapter12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class RegisterRequestValidator implements Validator{

	private static final String emailRegExp =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"+
			"@" + 
			"[A-Z-a-z0-9-]+(\\.[A-Za-zo-0]+)*(\\.[A-Za-z]{2,})$"; 
	
	private Pattern pattern;
	
	
	public RegisterRequestValidator() {
		pattern = Pattern.compile(emailRegExp);
		// 패턴에 맞는 코드가 맞는지 확인하도록 연결함
		
	}
	
	// Validator에 검증할 수 있는 커맨드 객체인지 알려주는 메서드
	@Override
	public boolean supports(Class<?> clazz) { // 스프링이 자동으로 해줌
		// 매개변수(clazz)로 전달된 객체를 RegisterRequest 타입으로 바꿔보는 코드
		return RegisterRequest.class.isAssignableFrom(clazz);
		// RegisterRequest이 클래스 명을 통해서 이 클래스를 검증하는 발리데이터이구나를 알 수 있음
	}
	
	// 우리의 의도에 맞게 커맨드 객체를 검증하는 코드를 만들어줘야 하는 메서드
	// - 검사 대상 객체의 특정 멤버 변수나 상태가 올바른지 검사
	// - 올바르지 않다면 Erros의 rejectValue() 메서드를 사용해 에러 코드를 저장
	// 일반적으로 validate 메서드는 위와 같이 구현함
	@Override
	public void validate(Object target, Errors errors) {
		//첫 번째는 
		// 두번째는 결과를 저장하는 개체, 
		RegisterRequest regReq = (RegisterRequest) target;
		
		if(regReq.getEmail()==null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");// 이메일의 에러 코드로 리콰이어를 저장
		}else {
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) {
				// 이메일의 형식이 아니였다면
				errors.rejectValue("email","bad");
				// 이메일 멤버 변수에 배드라는 에러코드를 저장
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		// 두 번째 인자를 보고 커맨드 객체의 네임 멤버 변수가 이프문으로 표현한걸 이 메서드로 대체할 수 있음. 
		// 위의 코드가 들어있어서 위처럼 사용가능
		// 커맨드 객체와 관련된 인자가 없음 -> 사실 이 커맨드 객체가 가지고 있는 네임 멤버 변수를 검증해줌
		// 검증을 해야할 커맨드 객체가 에러스 안에 들어있음, 스프링이 알아서 검증해줌, 글떄 카겟과 에러스 다 전달해줌
		// 전달받은 네임이라는 에러 코드가 엠티이거나 화이트 스페이스라면 리콰이어로 에러 코드 저장
		
		// 
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "notmatch");
			}
		}
	}
	
}
