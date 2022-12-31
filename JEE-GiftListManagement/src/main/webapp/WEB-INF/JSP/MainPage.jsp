<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/style.css">
<title>Page principal</title>
</head>
<body>
	<jsp:include page="NavBar.jsp">
		<jsp:param value="1" name="pageIndex"/>
	</jsp:include>
	<h1>vous êtes sur la page principal</h1>
</body>
</html>