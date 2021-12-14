package chapter02;

// 의존객체를 생성하고 주입해주는 역할만 하는 클래스
//
// 변경의 여파를 더욱 더 한 곳으로 집중시켜주는 역할
//
// 객체를 생성하고 의존 객체를 주입하는 기능을 제공하고
// 객체가 필요한 곳에 객체를 제공함
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
