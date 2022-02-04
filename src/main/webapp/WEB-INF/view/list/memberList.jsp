<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="memberList.title"/></title>
</head>
<body>
	<form:form modelAttribute="cmd">
		<p>
			<label>from: <form:input path="from"/> </label>
			<form:errors path="from"/>
			
			~
			
			<label>to: <form:input path="to"/> </label>
			<form:errors path="to"/>
			
			<input type="submit" value="��ȸ">
		</p>
	</form:form>
	
	<c:if test="${! empty members }">
		<table>
			<tr>
				<th>���̵�</th>
				<th>�̸���</th>
				<th>�̸�</th>
				<th>������</th>
			</tr>
			
			<c:forEach var="mem" items="${members }">
				<tr>
					<td>${mem.id }</td>
					<td>
						<a href='<c:url value='/members/${mem.id}'/>'>${mem.email }</a>
					</td>
					<td>${mem.name }</td>
					<td>${mem.registerDateTime }</td>
				</tr>
			</c:forEach>
			
		</table>
	</c:if>
	
</body>
</html>