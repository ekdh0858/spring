package chapter15;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("chapter13")
public class CommonExceptionHandler {
	
	// 챕터13에서 이러한 예외가 발생했을 때는 이런 코드가 작동할 것이다.를 해준것
	@ExceptionHandler(MemberNotFoundException.class)
	public String handleMemberNotFoundException() {
		return "list/noMember";
	}
	
	
}
