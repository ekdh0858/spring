package chapter02;

public class Main2 {
// Assembler를 이용해서 수정한 Main1
	public static void main(String[] args) {
		RegisterRequest rr = new RegisterRequest();
		rr.setEmail("abc@gmail.com");
		rr.setPassword("1234");
		rr.setConfirmPassword("1234");
		rr.setName("홍길동");
		
		Assembler assembler = new Assembler();
		
		MemberRegisterService mrs = assembler.getMemberRegisterService();
		
		long newId = mrs.regist(rr);
		
		System.out.println("사용자의 식별값은 "+newId+"입니다.");
		
		
		System.out.println("사용자의 식별값은"+newId+"입니다.");
		
		System.out.println("= = = = =");
		
		ChangePasswordService cps = assembler.getChangePasswordService();
		
		String email="abc@gmail.com";
		String oldPW = "1234";
		String newPW = "5678";
		
		cps.ChangePasswordService(email, oldPW, newPW);
	}

}
