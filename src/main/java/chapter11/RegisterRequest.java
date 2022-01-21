package chapter11;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

// 회원가입을 처리할 때 필요한 이메일, 암호, 이름 데이터를 담고 있을 DTO
public class RegisterRequest {
	@NotBlank
	@Email
	private String email;
	@Size(min=6)
	private String password;
	@NotBlank
	private String confirmPassword;
	@NotBlank
	private String name;
	
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
