package chapter02;

public class ChangePasswordService {
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
