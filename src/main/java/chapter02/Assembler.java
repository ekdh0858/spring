package chapter02;

// 의존객체를 생성하고 주입해주는 역할만 하는 클래스
//
// 변경의 여파를 더욱 더 한 곳으로 집중시켜주는 역할
//
// 객체를 생성하고 의존 객체를 주입하는 기능을 제공하고
// 객체가 필요한 곳에 객체를 제공함

// 스프링은 Assembler 클래스의 생성자 코드처럼
// 필요한 객체를 생성하고 생성한 객체에 의존을 주입함
// 또한 스프링은 Assembler 클래스의 getter처럼 객체를 제공하는 기능을 갖고 있음
// Assembler 클래스와 스프링의 DI 차이는 스프링 DI가 더 유연한 범용 조립기 라는 점!
public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService mrs;
	private ChangePasswordService cps;
	
	public Assembler() {
		memberDao = new CashedMemberDao();
		
		mrs = new MemberRegisterService(memberDao);
		
		cps = new ChangePasswordService();
		cps.setMemberDao(memberDao);
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getMemberRegisterService() {
		return mrs;
	}

	public ChangePasswordService getChangePasswordService() {
		return cps;
	}
}
