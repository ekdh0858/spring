package chapter10;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	// @Transactional-> 트랜잭션을 지정하고 싶은 메서드에 이 애노테이션을 붙임
	// ChangePasswordService 메서드에 @Transactional을 붙여서
	// 이 메서드 안에 서 동작하는 쿼리(SelectByEmail, update)가 하나의 작업으로 묶임
	
	// 이 메서드에서 예외를 방생시키는 이유는 우리가 원하는 상황에서 rollback이 되도록 하기 위해서
	// 트랜잭션으로 묶인 코드를 실행 할 때 SQLException 계열의 예외가 발생할 수 있음
	// SQLException 계열의 예외는 RunTimeException 계열이 아님
	// RuntimeException 계열의 열외가 아닌 다른 계열의 예외가 발생하면 rollback이 이뤄지지 않음
	// rollbackFor 속성을 추가하면 지정한 예외가 발생했을 때도 rollback이 이뤄지도록 할 수 있음
	// norollbackFor 속성을 추가하면 지정한 예외가 발생했을 때는 rollback하지 않도록 함
	@Transactional(rollbackFor = {SQLException.class,IOException.class}) 
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
