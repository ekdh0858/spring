package chapter04;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("listPrinter")
public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	
	public MemberDao getMemberDao() {
		return memberDao;
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}


	public MemberPrinter getPrinter() {
		return printer;
	}

	// 스프링이 Autowired를 할 때 @Qualifier("printer") 애노테이션을 사용해서
	// 한정자가 printer인 빈을 찾아서 그 빈의 객체를 주입함
	@Autowired
	@Qualifier("printer1")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}


	public void printAll() {
		// 회원 가입 되어있는 사용자들의 정보
		Collection<Member> members = memberDao.selectAll();
		
		// 사용자들의 정보를 하나씩 전부 출력
		for(Member member:members) {
			printer.print(member);
		}
		System.out.println();
	}
}
