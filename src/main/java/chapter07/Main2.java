package chapter07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		ChangePasswordService cps = ctx.getBean(ChangePasswordService.class);
		
		try {
			cps.ChangePasswordService("12281105@est.com", "0x1.731c06ff7d53dp-1", "1111");
		}catch(MemberNotFoundException e) {
			System.out.println("이메일과 일치하는 회원 정보가 없습니다.");
		}catch(WrongIdPasswordException e) {
			System.out.println("암호가 올바르지 않습니다.");
		}
		ctx.close();
	}

}
