<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title><spring:message code="login.title"/></title>
</head>
<body>

<p>
	<spring:message code="login.done"/>
</p>

<p>
	<a href="<c:url value="/main" />">[<spring:message code="go.main"/>]</a>
</p>

</body>
</html>