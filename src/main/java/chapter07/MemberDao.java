package chapter07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
		
		//queryForobject 메서드는 실행 결과가 무조건 있어야하는 상황에서 쓰는 것
		// 아래 코드는 queryForobject를 이렇게도 쓸 수 있다 를 보기 위해 입력하는 것이므로
		// 이 코드를 활용해서 필요한 곳에서 사용하면 됨
		
		// 쿼릐 실행 결과에 칼럼이 하나가 아니라 여러 개 일 경우에는 아래와 같이 RowMapper를 활용해서 결과를 반환하도록 할 수 있음
//		Member member = jdbcTemplate.queryForObject(sql, new MemberMapper(), email);
//		return member;
	}
	
	public void insert(Member member) {
		
		
	//3번 할 때 주석처리	jdbcTemplate.update(sql,member.getEmail(),member.getPassword(),member.getName(),Timestamp.valueOf(member.getResisterDateTime()) );
		
		// 방금 INSERT한 사용자의 아이디 칼럼 값을 알고 싶다
		// 이런 문제를 해결할 수 있는 방법은?
		// 방금 INSERT한 사용자의 이메일(식별값)을 사용해서 정보를 조회해보면 됨 
		// 1. 첫 번째 방법
		
//		sql = "SELECT * FROM member WHERE email =?";
//		int id = jdbcTemplate.queryForObject(sql, Integer.class,member.getEmail());
		
		// 2. 두 번째 방법
//		Member insertedMember = selectByEmail(member.getEmail());
//		int id = (int) insertedMember.getId();
		
		// 3. 세 번째 방법 : PreparedStatement를 활용해서 INSERT 시 생성된 AUTO_INCREMENT 칼럼의 값을 가져올 수 있음
		// SELECT를 하지 않아도 INSERT 시 AUTO_INCREMENT 칼럼의 값을 가져올 수 있도록 해주는 객체
		// 대신 keyHolder를 사용하려면 PreParedAtatement로 쿼리를 실행해야함
		KeyHolder keyholder = new GeneratedKeyHolder();
		
		// keyHolder에 AUTO_INCREMENT 칼럼의 값을 저장하기 위해서 update 메서드의 두 번째 인자로 넣었음
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "INSERT INTO member(email,password,name,regdate) VALUES(?,?,?,?)";
				
				
				//preparedStatemen 메서드의 두 번쨰 인자로 String 타입의 배열을 넣음
				// 가져올 AUTO_INCREMENT 속성이 붙은 칼럼 이름들을 String 타입의 배열에 넣음
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"ID"});
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getResisterDateTime()));
				
				return pstmt;
			}
			
		}, keyholder);
		
		// keyholder에 들어있는 값을 꺼낼 때 getKey
		// keyholder에 들어있는 값들을 꺼낼 때 getKeyList 또는 getKeys
		
		// Number 클래스는 래퍼 클래스의 하나
		Number keyValue = keyholder.getKey();
		
		int id = keyValue.intValue();
		
		System.out.println("회원 가입한 사용자의 식별 값 ID=>"+id);
	}
	public void update(Member member) {
		// INSERT, UPDATE, DELETE 쿼리를 실행할 때는 update 메서드를 호출해야함
		
//		String sql = "UPDATE member SET name = ?, password = ?, WHERE email = ?";
//		jdbcTemplate.update(sql, member.getName(),member.getPassword(),member.getEmail());
		
		// 상황에 따라서는 자바의 JDBC에서 사용한 PreparedStatement를 사용해서 쿼리를 구성하고 인덱스 파라미터에 값을 넣어서 쿼리를 실행해야하는 경우도 있음
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "UPDATE member SET name = ?, password = ? WHERE email = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getName());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getEmail());
				
				return pstmt;
			}
		});
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
		String sql = "SELECT COUNT(*) FROM member";
		
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
