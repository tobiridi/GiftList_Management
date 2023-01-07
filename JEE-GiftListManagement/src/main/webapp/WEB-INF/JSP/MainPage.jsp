<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="be.Jadoulle_Declercq.JavaBeans.GiftList" %>
<%@ page import="be.Jadoulle_Declercq.JavaBeans.NotificationMessage" %>
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
	<jsp:useBean id="customerLog" class="be.Jadoulle_Declercq.JavaBeans.Customer" scope="session"></jsp:useBean>
	<jsp:include page="NavBar.jsp">
		<jsp:param value="1" name="pageIndex"/>
	</jsp:include>
	
	<h1>Vous êtes sur la page principal</h1>
	<h2>Vos listes</h2>
	<hr>
	<% if(!customerLog.getGiftList().isEmpty()) { %>
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
					<td><%= list.isExpired() ? "expiré" : "actif" %></td>
					<td>
						<a href="ModifyGiftList?idGiftList=<%= list.getId() %>"><input type="button" value="Modifier"/></a>
						<a href="DeleteGiftList?idGiftList=<%= list.getId() %>"><input type="button" value="Supprimer"/></a>
						<% if(!list.isExpired()) { %>
							<a href="DetailsGiftList?idGiftList=<%= list.getId() %>"><input type="button" value="Détails"/></a>
						<% } %>
					</td>
				</tr>
			<% } %>
		</tbody>
		</table>
	<% } else { %>
		<p style="text-align: center">Vous n'avez aucune liste.</p>
	<% } %>
	
	<h2>Listes des amis</h2>
	<hr>
	<% if(!customerLog.getOtherCustomerList().isEmpty()) { %>
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
					<td><%= list.isExpired() ? "expiré" : "actif" %></td>
					<% if(!list.isExpired()) { %>
						<td>
							<%--TODO : add url --%>
							<a href="DetailsGiftList?idFriendGiftList=<%= list.getId() %>"><input type="button" value="Détails"/></a>
						</td>
					<% } %>
				</tr>
			<% } %>
		</tbody>
		</table>
	<% } else { %>
		<p style="text-align: center">Il n'y a aucune liste.</p>
	<% } %>
</body>
</html>