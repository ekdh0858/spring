<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title><spring:message code="change.pwd.title"></spring:message></title>
</head>
<body>

	<p>
		<spring:message code="change.pwd.done"></spring:message>
	</p>
	<p>
		<a htef="<c:url value="/main"/>">[<spring:message code="go.main"></spring:message>]</a>
	</p>

</body>
</html>