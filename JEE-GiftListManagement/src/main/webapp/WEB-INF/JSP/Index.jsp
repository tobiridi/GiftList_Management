<%@page import="java.util.HashMap"%>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./CSS/style.css">
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
	<%
		HashMap<String, String> errorsMessage = (HashMap<String, String>) request.getAttribute("errorsMessage");
		String previousEmail = (String)request.getAttribute("previousEmail");
		String previousPassword = (String)request.getAttribute("previousPassword");
	%>

	<h1>Connexion au site GiftListManagement</h1>
	<form action="Index" method="post" class="loginForm">
	<%
		if(previousEmail == null && previousPassword == null) {
	%>
	<p><label for="email">Email </label> : <input type="email" id="email" name="email" placeholder="Email" required="required"/></p>
	<p><label for="password">Mot de passe </label> : <input type="password" id="password" name="password" placeholder="Mot de passe" required="required"/></p>
	<%
		}
		else {
	%>
	<p><label for="email">Email </label> : <input type="email" id="email" name="email" placeholder="Email" required="required" value="<%= previousEmail %>"/></p>
	<p><label for="password">Mot de passe </label> : <input type="password" id="password" name="password" placeholder="Mot de passe" required="required" value="<%= previousPassword %>"/></p>
	<%
		}
	%>
		<input type="submit" value="Connexion" />
		<%-- TODO : not implemented --%>
		<a href="Inscription"><input type="button" value="Inscription"/></a>
	</form>
	<div class="errorMessage">
		<%
		if(errorsMessage != null) {
			for(Map.Entry<String,String> error : errorsMessage.entrySet()) {
		%>
		<p><%= error.getValue() %></p>
		<%
			}
		}
		%>				
	</div>
</body>
</html>