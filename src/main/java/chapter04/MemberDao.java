package chapter04;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

// 컴포넌트 스캔 기능이 동작하려면 
// 빈으로 등록할 클래스에 @Component 애노테이션을 붙여야함

@Component
public class MemberDao {
	private static long nextId=0;
	
	private Map<String,Member> map = new HashMap<>();
	
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	public Collection<Member> selectAll(){
		return map.values();
	}
	
}
