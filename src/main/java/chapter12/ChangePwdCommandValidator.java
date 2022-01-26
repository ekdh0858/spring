package chapter12;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//ChangePwdCommand 객체의 currentPassword가 비어있거나 공백문자만 들어있을 경우
// currentPassword의 에러 코드를 required 로 지정

//changePwdCommand 객체의 newPassword가 비어잇다면
// newPassword의 에러 코드를 required로 지정

public class ChangePwdCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePwdCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
	}

}
