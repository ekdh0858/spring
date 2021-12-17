package chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter2 {
	private MemberDao memberDao;
	private MemberPrinter2 printer;
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null){
			System.out.println("일치하는 회원 정보 없음\n");
			return;	
		}
		printer.print(member);
		System.out.println();
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}
	// 세터 메서드에 AutoWired 애노테이션을 붙임
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	
	@Autowired
//	@Qualifier("printer2")
	public void setPrinter(MemberPrinter2 printer) {
		this.printer = printer;
	}
}
