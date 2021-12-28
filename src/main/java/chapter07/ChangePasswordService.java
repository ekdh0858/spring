package chapter07;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	// @Transactional-> 트랜잭션을 지정하고 싶은 메서드에 이 애노테이션을 붙임
	// ChangePasswordService 메서드에 @Transactional을 붙여서
	// 이 메서드 안에 서 동작하는 쿼리(SelectByEmail, update)가 하나의 작업으로 묶임
	@Transactional 
	public void ChangePasswordService(String email,String oldPW, String newPW) 
			throws MemberNotFoundException,WrongIdPasswordException{
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPW,newPW);
		
		memberDao.update(member);
	}
}
