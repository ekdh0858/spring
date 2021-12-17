package chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 두 개 이상의 의존 객체가 있음
// 하나의 세터로 여러 개의 의존 객체를 주입하는게 아니라
// 의존 객체 마다 세터를 만들어서 의존 주입을 하도록 해야함!

//@Component 애노테이션에 값을 주지 않으면 
// 빈으로 등록 될 때 클래스 이름의 첫 글자를 소문자로 바꾼 이름을 빈 이름으로 사용
@Component("infoPrinter")
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

	public MemberDao getMemberDao() {
		return memberDao;
	}
	// 세터 메서드에 AutoWired 애노테이션을 붙임
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public MemberPrinter getPrinter() {
		return printer;
	}
	@Autowired
//	@Qualifier("printer2")
	public void setPrinter(MemberSummaryPrinter printer) {
		this.printer = printer;
	}
}
