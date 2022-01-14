<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>설문조사</title>
</head>
<body>
	<h2>설문조사</h2>
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
		<p>3.하고 싶은 말<br>
			<input type="text" name="responses[2]">
		</p>
		<p>
		<label>
				응답자의 지역 : <input type="text" name="res.location">
		</label>
		</p>
		<p>
		<label>
				응답자의 나이 : <input type="text" name="res.age">
		</label>
		</p>
		<input type="submit" value="전송">
	</form>
</body>
</html>