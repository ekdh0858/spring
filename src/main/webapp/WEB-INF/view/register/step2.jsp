<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/>(2)</title>
</head>
<body>
	<!-- modelAttribute 속성 : 이 form 태그 안에서 사용할 커맨드 객체 이름-->
	<form:form action="step3" modelAttribute="registerRequest">
		<input type="hidden" name="hiddenEmail" value="${registerRequest.email} }">
		<p>
			<label><spring:message code="email"/>:<br>
<%-- 				<input type="text" name="email" id="email" value ="${registerRequest.email} "> --%>
				<!-- form:input 커스텀 태그 : html의 type이 text인 input 태그 -->
				<!-- path속성 : 지정한 커맨드 객체의 멤버 변수 값을 이 태그의 value 속성값으로 사용하겠다. -->
				<form:input path="email"/>
				<form:errors path="email"></form:errors>
			</label>
		</p>
		<p>
		<label><spring:message code="name"/>:<br>
<%-- 				<input type="text" name="name" id="name" value ="${registerRequest.name} "> --%>
				<form:input path="name"/>
				<form:errors path="name"/>
			</label>
		</p>
		<p>
		<label><spring:message code="password"/>:<br>
<%-- 				<input type="text" name="password" id="password" value ="${registerRequest.pw}"> --%>
				<!-- form:password 커스텀 태그 : html의 type이 password인 input 태그 -->
				<form:password path="password"/>
				<form:errors path="password"></form:errors>
			</label>
		</p>
		<p>
		<label><spring:message code="password.confirm"/>:<br>
<!-- 				<input type="text" name="confirmPassword" id="confirmPassword"> -->
				<form:password path="confirmPassword"/>
				<form:errors path="confirmPassword"></form:errors>
			</label>
		</p>
		<input type="submit" value="<spring:message code="register.btn"/>">
		</form:form>
	
</body>
</html>