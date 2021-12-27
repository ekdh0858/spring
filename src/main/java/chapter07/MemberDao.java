package chapter07;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

// 스프링을 사용하면 Connection, PreparedStatement, ResultSet 등을 직접 사용하지 않고
// jdbcTemplate 을 이용해서 편리하게 쿼리를 실행할 수 있음

public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) {
		String sql = "SELECT * FROM member WHERE email = ?";		
		
		// SELECT의 실행 결과를 0개 이상이므로 List타입에 결과를 저장하도록 했음
		// new RowMapper<Member>() {...} -> 인스턴스를 만들면서 동시에 구현 / 익명 클래스
		List<Member> result = jdbcTemplate.query(sql, new MemberMapper(), email);
		return result.isEmpty() ? null : result.get(0);
	}
	
	public void insert(Member member) {
	}
	public void update(Member member) {
	}
	
	public Collection<Member> selectAll(){
		String sql = "SELECT * FROM member";
		
		// query 메서드의 두 번째 인자로 제네릭스 타입이 Member인 익명 클래스를 선언
		// 제네릭스 타입이 Member인 익멸 클래스를 선언? query 메서드의 결과를 저장할 변수의 제네릭스 타입이 Member이므로 맞춰서 사용해야함
		Collection<Member> result = jdbcTemplate.query(sql, new MemberMapper());
		
		return result.isEmpty() ? null:result;
	}
	
	// 회원가입한 사용자들의 수
	public int count() {
		// 아래의 쿼리는 실행 결과가 무조건 있음
		// SELECT 쿼리의 실행 결과가 무조건 있고 실행 결과가 한 행일 때는 queryForobject 메서드를 사용하면 쉽게 결과를 가져올 수 있음
		String sql = "SELECR COUNT(*) FROM member";
		
//		List<Integer> result = jdbcTemplate.query(sql, new RowMapper<Integer>() {
//			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
//				return rs.getInt(1);
//			}
//			
//		});
		
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
				
		
		return count;
	}
}
