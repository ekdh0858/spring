package chapter12;

import java.time.LocalDateTime;


// 회원 정보
// DTO 또는 VO라고 부름
// DTO - Data Transfer object
// VO - Value Object
// 클라이언트가 보낸 데이터들을 저장해 활용할 수 있게 하거나
// DB 쿼리 결과 데이터들을 저장해 활용할 수 있게 만들어주는 용도
public class Member {
	private long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime resisterDateTime;
	
	public Member(String email, String password, String name, LocalDateTime resisterDateTime) {
		this.email = email;
		this.password = password;
		this.name=name;
		this.resisterDateTime = resisterDateTime;
	}
	
	// 비밀번호 변경 메서드
	// oldPW -> 현재 비밀번호
	// newPW -> 변경 할 비밀번호
	public void changePassword(String oldPW,String newPW) {
		if(!password.equals(oldPW)) {
			throw new WrongIdPasswordException();
		}
		password = newPW;
	}
	
	public boolean matchpassword(String password) {
		return this.password.equals(password);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getResisterDateTime() {
		return resisterDateTime;
	}

	public void setResisterDateTime(LocalDateTime resisterDateTime) {
		this.resisterDateTime = resisterDateTime;
	}
}
