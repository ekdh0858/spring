package chapter03;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

// @Autowire(required=false)와 @Nullable의 차이
// Nullable 애노테이션을 사용하면 자동 주입할 빈이 존재하지 않아도 메소드가 호출됨
// @Autowired(required=false) 애노테이션을 사용하면 자동 주입할 빈이 존재하지 않으면 메서드가 호출되지 않음

public class MemberPrinter {
	
	@Autowired
	@Nullable
	private DateTimeFormatter dtf;
//	@Autowired
//	private Optional<DateTimeFormatter> opt;
	
	public void print(Member member) {		
		
		String text1 = "아이디 = "+member.getId();
		String text2 = "이메일 = "+member.getEmail();
		String text3 = "이름 = "+member.getName();
		String text4 = "등록일 ="+member.getResisterDateTime();
		
		// if문 덕분에 의존 객체에 완전히 의존하는 구조가 아니라
		// 의존객체가 있다면 의존하고 의존 객체가 없다면 의존하지 않음
		// 의존 객체가 있지만 의존 주입이 필수는 아님
		if(dtf!=null) {
			text4 = "등록일 ="+dtf.format(member.getResisterDateTime());
		}
//		if(opt.isPresent()) {
//			DateTimeFormatter dft = opt.get();
//			text4 = "등록일 ="+dft.format(member.getResisterDateTime());
//		}
		
		String text = "회원정보 : "+text1+","+text2+","+text3+","+text4;
		
		System.out.println(text);
	}
	
	// @Autowired 애노테이션이 붙으면 스프링은 무조건 컨테이너 안에서 적절한 빈을 찾아서 의존 주입을 함
	// 적절한 빈이 없으면 예외를 발생시킴
	// 자동 의존 주입이 필수가 아닐 때는 @Autowired 애노테이션에 required 속성을 false로 주면 됨
//	@Autowired(required=false)
//	public void setDateTimeFormatter(DateTimeFormatter dtf) {
//		this.dtf = dtf;
//	}
	
	// 스프링 5부터는 @Autowired 애노테이션의 required 속성 대신
	// 자바8의 Optional을 사용할 수 있음
//	@Autowired(required=false)
//	public void setDateTimeFormatter(Optional<DateTimeFormatter> dtfOption) {
//		// dtfOption 객체에 DateTimeFormatter타입의 객체가 들어있나요?
//		if(dtfOption.isPresent()){
//			this.dtf = dtfOption.get();
//		}
//	}
	
	// 자동 의존 주입이 필수가 아닐 때 적용할 수 있는 방법 3
	// @Nullable 애노테이션을 사용 / 이 애노테이션은 매개변수에 붙임
	// @Nullable 애노테이션을 붙인 세터 메서드에서 @Nullable 애노테이션을 의존 주입 대상 매개변수에 붙이면
	// 스프링 컨테이너가 자동 의존 주입을 시도할 때
	// 매치되는 의존 객체가 존재하면 해당 객체를 인자로 전달
	// 존재하지 않으면 인자로 NULL을 전달
//	@Autowired
//	public void setDateTimeFormatter(@Nullable DateTimeFormatter dtf) {
//		this.dtf = dtf;
//	}
}
