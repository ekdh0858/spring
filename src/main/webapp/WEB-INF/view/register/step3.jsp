<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(3)</title>
</head>
<body>
	
	<p>
		<spring:message code="register.done">
		<!-- regidter.done 메세지는 파라미터를 갖고 있음 -->
		<!-- 인덱스 파라미터는 전달해주는 값을 순서에 맞게 지정한 위치에 출력해주는 기능 -->
		<!--  컴퓨터는 0부터 숫자를 세므로{0} 자리에 첫 번째로 전달한 값이 출력됨 -->
		<!--  메세지의 인덱스 파라미터로 값을 전달할 떄는 argument 커스텀 태그를 사용함 -->
			<spring:argument value="${registerRequest.name },${registerRequest.email }"/>
		
		<!-- 인덱스 파라미터가 두 개 이상일 경우 인덱스 파라미터로 값을 전달하는 방법 -->
		<!-- 1. 콤마로 구분한 문자열 -->
		<!-- 2. 객체 배열 -->
		<!-- 3. <spring:argument> 태그 사용 -->
			<spring:argument value="${registerRequest.email }"/>
		</spring:message>
		
		
	</p>
	<p><a href="http://localhost/sp5/main">[<spring:message code="go.main"/>]</a></p>
	
	
</body>
</html>