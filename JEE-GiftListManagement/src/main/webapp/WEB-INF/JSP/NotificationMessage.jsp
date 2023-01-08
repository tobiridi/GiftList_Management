<%@ page import="java.util.ArrayList"%>
<%@ page import="be.Jadoulle_Declercq.JavaBeans.NotificationMessage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/style.css">
<title>Messages</title>
</head>
<body>
	<jsp:useBean id="customerLog" class="be.Jadoulle_Declercq.JavaBeans.Customer" scope="session"></jsp:useBean>
	<jsp:include page="NavBar.jsp">
		<jsp:param value="4" name="pageIndex"/>
	</jsp:include>
	
	<h1>Liste des messages</h1>
	<hr>
	<% if(!customerLog.getMessageBox().isEmpty()) { %>
		<table>
			<thead>
				<tr>
					<th>Titre</th>
					<th>Message</th>
					<th>Date de réception</th>
					<th>Option</th>
				</tr>
			</thead>
			<tbody>
				<% for(NotificationMessage message : customerLog.getMessageBox()) { %>
				<tr>
					<td><%= message.getTitle() %></td>
					<td><textarea rows="5" cols="20" readonly="readonly" style="border: none"><%= message.getMessage() %></textarea></td>
					<td><%= message.getNotificationDate() %></td>
					<td><a href="DeleteNotificationMessage?idNotification=<%= message.getId() %>"><input type="button" value="Supprimer" /></a></td>
				</tr>
				<% } %>
			</tbody>
		</table>
	<% } else { %>
		<p style="text-align: center">Il n'y a aucun message.</p>
	<% } %>
</body>
</html>