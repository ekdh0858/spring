package chapter07;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogDao {
	private JdbcTemplate jdbcTemplate;
	
	public LogDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public void insertLog(long memberIdx, int log_category,String message) {			
		String sql = "INSERT spring INTO log(member_idx,log_category,message,regdate) VALUES(?,1, \"회 원 가 입\", CURRENT_TIMESTAMP())";
		
		jdbcTemplate.update(sql,memberIdx,log_category, message);
	}
}
