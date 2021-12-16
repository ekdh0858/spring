package chapter03;

public class MemberPrinter {
	public void print(Member member) {
		String text1 = "아이디 = "+member.getId();
		String text2 = "이메일 = "+member.getEmail();
		String text3 = "이름 = "+member.getName();
		String text4 = "등록일 ="+member.getResisterDateTime();
		
		String text = "회원정보 : "+text1+","+text2+","+text3+","+text4;
		System.out.println(text);
	}
}
