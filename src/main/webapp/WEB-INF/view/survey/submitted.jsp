<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 결과</title>
</head>
<body>
	<p>응답 내용 :</p>
	<ul>
		<li>1번 문항 : ${ansData.responses[0]}</li>
		<li>2번 문항 : ${ansData.responses[1]}</li>
		<li>3번 문항 : ${ansData.responses[2]}</li>
	</ul>
	<p>응답자 위치 : ${ansData.res.location }</p>
	<p>응답자 나이 : ${ansData.res.age }</p>
	
</body>
</html>