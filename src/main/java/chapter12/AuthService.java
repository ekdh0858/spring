package chapter12;

public class AuthService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public AuthInfo authenticate(String email, String password) throws WrongIdPasswordException{
		// 한번에 여러가지의 기능이 실행됨. 여러개 대신에 분리해서 하나한 해주는것도 좋을듯
		Member member = memberDao.selectByEmail(email);
		
		if(member==null) {
			throw new WrongIdPasswordException();
		}
		
		if(!member.matchpassword(password)) {
			throw new WrongIdPasswordException();
		}
		
		return new AuthInfo(member.getId(),member.getEmail(),member.getName());
	}
}
