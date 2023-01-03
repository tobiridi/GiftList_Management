<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page import="be.Jadoulle_Declercq.JavaBeans.GiftList" %>
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
	<jsp:useBean id="customerLog" class="be.Jadoulle_Declercq.JavaBeans.Customer" scope="session"></jsp:useBean>
	
	<h1>vous êtes sur la page principal</h1>
	<h2>Vos listes</h2>
	<hr>
	<table>
	<thead>
		<tr>
			<th>Type</th>
			<th>Date limite</th>
			<th>Active</th>
			<th>Option</th>
		</tr>
	</thead>
	<tbody>
		<% for(GiftList list : customerLog.getGiftList()) { %>
			<tr>
				<td><%= list.getType() %></td>
				<td><%= list.getDeadLine() != null ? list.getDeadLine().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "aucune date défini" %></td>
				<td><%= list.isActive() ? "actif" : "non actif" %></td>
				<%--TODO : add url --%>
				<td>
					<a href="#"><input type="button" value="Modifier"/></a>
					<a href="#"><input type="button" value="Supprimer"/></a>
					<a href="#"><input type="button" value="Détails"/></a>
				</td>
			</tr>
		<% } %>
	</tbody>
	</table>
	
	<h2>Listes des amis</h2>
	<hr>
	<table>
	<thead>
		<tr>
			<th>Type</th>
			<th>Date limite</th>
			<th>Active</th>
			<th>Option</th>
		</tr>
	</thead>
	<tbody>
		<% for(GiftList list : customerLog.getOtherCustomerList()) { %>
			<tr>
				<td><%= list.getType() %></td>
				<td><%= list.getDeadLine() != null ? list.getDeadLine().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "aucune date défini" %></td>
				<td><%= list.isActive() ? "actif" : "non actif" %></td>
				<%--TODO : add url --%>
				<td><a href="#"><input type="button" value="Détails"/></a></td>
			</tr>
		<% } %>
	</tbody>
	</table>
</body>
</html>