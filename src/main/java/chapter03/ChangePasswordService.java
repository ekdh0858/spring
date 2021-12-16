package chapter03;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {
	// 자동 의존 주입을 사용하면 번거롭게 개발자가 직접 의존 주입을 할 필요가 없다
	// 자동 의존 주입 애노테이션이 붙은 멤버 변수의 타입을 사용해서 의존 주입할 빈을 찾음
	@Autowired
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void ChangePasswordService(String email,String oldPW, String newPW) 
			throws MemberNotFoundException,WrongIdPasswordException{
		//비밀번호를 변경할 회원 정보를 조회
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		// 비밀번호 변경
		member.changePassword(oldPW,newPW);
		
		memberDao.update(member);
	}
}
