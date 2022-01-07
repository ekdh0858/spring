<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(2)</title>
</head>
<body>
	<form action="step3" method="POST">
		<p>
			<label>이메일:<br>
				<input type="text" name="email" id="email">
			</label>
		</p>
		<p>
		<label>이름:<br>
				<input type="text" name="name" id="name">
			</label>
		</p>
		<p>
		<label>비밀번호:<br>
				<input type="text" name="password" id="password">
			</label>
		</p>
		<p>
		<label>비밀번호 확인:<br>
				<input type="text" name="confirmPassword" id="confirmPassword">
			</label>
		</p>
		<input type="submit" value="가입 완료">
	</form>
</body>
</html>