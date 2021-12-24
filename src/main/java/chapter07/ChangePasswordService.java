package chapter07;

public class ChangePasswordService {
	private MemberDao memberDao;
	
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
