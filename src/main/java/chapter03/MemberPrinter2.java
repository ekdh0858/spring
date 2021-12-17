package chapter03;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter2 {
	
	@Autowired
	@Nullable
	private DateTimeFormatter dtf;
	
	public MemberPrinter2() {
		dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}
	
	public void print(Member member) {		
		
		String text1 = "아이디 = "+member.getId();
		String text2 = "이메일 = "+member.getEmail();
		String text3 = "이름 = "+member.getName();
		String text4 = "등록일 ="+member.getResisterDateTime();
		
		if(dtf!=null) {
			text4 = "등록일 ="+dtf.format(member.getResisterDateTime());
		}
		
		String text = "회원정보 : "+text1+","+text2+","+text3+","+text4;
		
		System.out.println(text);
	}
	
//	@Autowired(required=false)
//	public void setDateTimeFormatter(DateTimeFormatter dtf) {
//		this.dtf = dtf;
//	}
	
	@Autowired
	public void setDateTimeFormatter(@Nullable DateTimeFormatter dtf) {
		
		this.dtf = dtf;
	}
}
