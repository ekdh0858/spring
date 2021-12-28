package chapter07;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	private static MemberDao memberDao;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
	private static String prefix = formatter.format(LocalDateTime.now());
	
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		memberDao = ctx.getBean(MemberDao.class);
		
		slelectAll();
		// insertmember();
		updateMember();
		
		ctx.close();
		
	}


	private static void updateMember() {
		System.out.println("-----UPDATE MEMBER----");
		Member member = memberDao.selectByEmail("1228110605@est.com");
		
		String oldPW = member.getPassword();
		String newPW = Double.toHexString(Math.random());
		
		member.changePassword(oldPW, newPW);
		
		memberDao.update(member);
	}


	private static void insertmember() {
		System.out.println("-----INSERT MEMBER -----");
		
		Member member = new Member(prefix+"@est.com",prefix,prefix,LocalDateTime.now());
		
		memberDao.insert(member);
	}


	private static void slelectAll() {
		System.out.println("-----SELECT ALL -----");
		
		int total = memberDao.count();
		System.out.println("회원 가입된 사용자의 수 : "+total);
		
		Collection<Member> member = memberDao.selectAll();
		if(member != null) {
			for(Member m:member) {
				System.out.println(m.getId() + " : "+m.getEmail()+":"+m.getName());
			}
		}
		System.out.println();
	}

}
