package chapter07;

public class MemberInfoPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null){
			System.out.println("일치하는 회원 정보 없음\n");
			return;	
		}
		printer.print(member);
		System.out.println();
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public MemberPrinter getPrinter() {
		return printer;
	}
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}
