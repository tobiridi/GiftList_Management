<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/style.css">
<title>Détails liste</title>
</head>
<body>
	<jsp:useBean id="detailGiftList" class="be.Jadoulle_Declercq.JavaBeans.GiftList" scope="request"></jsp:useBean>
	<jsp:include page="NavBar.jsp">
		<jsp:param value="3" name="pageIndex"/>
	</jsp:include>
	
	<h1>Détails de la liste</h1>
	<%--TODO not finished --%>
</body>
</html>