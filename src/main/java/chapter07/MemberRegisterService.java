package chapter07;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

public class MemberRegisterService {
	private MemberDao memberDao;
	private LogDao logDao;
	
	public MemberRegisterService(MemberDao memberDao, LogDao logDao) {
		this.memberDao=memberDao;
		this.logDao=logDao;
	}
	
	@Transactional
	// SELECT 쿼리는 DB내 데이터에  직접 영향을 주는 쿼리가 아님
	public long regist(RegisterRequest request) throws DuplicateMemberException{
		//가입하려는 사용자의 이메일로 회원 정보를 조회
		Member member = memberDao.selectByEmail(request.getEmail());
		
		if(member!=null) {
			// 같은 이메일을 가진 회원이 이미 존재한다면 예외 발생
			throw new DuplicateMemberException("dup email"+request.getEmail());
		}
		
		//같은 이메일을 가진 회원이 존재하지 않는다면
		Member newMember = new Member(
				request.getEmail(),request.getPassword(),request.getName(),
				LocalDateTime.now()
				);
		
		long newMemberId = memberDao.insert(newMember);
		
		System.out.println("멈춤");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("시작");
		
		logDao.insertLog(newMemberId,1,"회원가입");
		
		return newMemberId;
	}

	public void delete(String email) throws WrongIdPasswordException{
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new WrongIdPasswordException();
		}
		memberDao.delete(email);		
		
		logDao.insertLog(member.getId(),2,"회원탈퇴");
		
	}
}
