package chapter04;

import org.springframework.stereotype.Component;

@Component("printer2")
public class MemberSummaryPrinter extends MemberPrinter{

	@Override
	public void print(Member member) {
		String text1 = "아이디 = "+member.getId();
		String text2 = "이메일 = "+member.getEmail();
		
		String text = "회원정보 : "+text1+","+text2;
		
		System.out.println(text);
	}

}
