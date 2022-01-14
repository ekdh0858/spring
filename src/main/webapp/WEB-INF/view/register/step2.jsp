<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(2)</title>
</head>
<body>
	<!-- modelAttribute 속성 : 이 form 태그 안에서 사용할 커맨드 객체 이름-->
	<form:form action="step3" modelAttribute="registerRequest">
		<p>
			<label>이메일:<br>
<%-- 				<input type="text" name="email" id="email" value ="${registerRequest.email} "> --%>
				<!-- form:input 커스텀 태그 : html의 type이 text인 input 태그 -->
				<!-- path속성 : 지정한 커맨드 객체의 멤버 변수 값을 이 태그의 value 속성값으로 사용하겠다. -->
				<form:input path="email"/>
			</label>
		</p>
		<p>
		<label>이름:<br>
<%-- 				<input type="text" name="name" id="name" value ="${registerRequest.name} "> --%>
				<form:input path="name"/>
			</label>
		</p>
		<p>
		<label>비밀번호:<br>
<%-- 				<input type="text" name="password" id="password" value ="${registerRequest.pw}"> --%>
				<!-- form:password 커스텀 태그 : html의 type이 password인 input 태그 -->
				<form:password path="password"/>
			</label>
		</p>
		<p>
		<label>비밀번호 확인:<br>
<!-- 				<input type="text" name="confirmPassword" id="confirmPassword"> -->
				<form:password path="confirmPassword"/>
			</label>
		</p>
		<input type="submit" value="가입 완료">
		</form:form>
	
</body>
</html>