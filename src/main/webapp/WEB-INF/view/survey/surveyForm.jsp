<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��������</title>
</head>
<body>
	<h2>��������</h2>
	<form method="post">
		<c:forEach var="question" items="${questions }" varStatus="status">
			<p>
				${status.count }. ${question.title }<br>
				<c:if test="${question.choice }">
					<c:forEach var="option" items=${question.options }>
						<label>
							<input type="radio" name="responses[${status.index }]" value=${option }>
							${option}
						</label>
					</c:forEach>
				</c:if>
				<c:if test="${!question.choice }">
					<input type="text">
				</c:if>
			
			</p>
		</c:forEach>
		<p>3.�ϰ� ���� ��<br>
			<input type="text" name="responses[2]">
		</p>
		<p>
		<label>
				�������� ���� : <input type="text" name="res.location">
		</label>
		</p>
		<p>
		<label>
				�������� ���� : <input type="text" name="res.age">
		</label>
		</p>
		<input type="submit" value="����">
	</form>
</body>
</html>