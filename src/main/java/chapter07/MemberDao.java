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
		String sql = "SELECT * FROM member";
		
		// SELECT의 실행 결과를 0개 이상이므로 List타입에 겨로가를 저장하도록 했음
		List<Member> result = jdbcTemplate.query(sql, new RowMapper<Member>() {
		
		@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
				String email = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				LocalDateTime regdate = rs.getTimestamp("regdate").toLocalDateTime();
				
				Member member = new Member(email, password,name,regdate);
				return member;
			}
		
	
	});
		return result.isEmpty() ? null : result.get(0);
	}
	
	public void insert(Member member) {
	}
	public void update(Member member) {
	}
	
	public Collection<Member> selectAll(){
		return null;
	}
	
}
