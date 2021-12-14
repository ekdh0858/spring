package chapter02;

import java.util.HashMap;
import java.util.Map;

//DB의 역활과 DAO의 역활을 하는 MemberDao
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
}
