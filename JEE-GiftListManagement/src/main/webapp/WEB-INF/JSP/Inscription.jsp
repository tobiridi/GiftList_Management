<%@page import="java.util.HashMap"%>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./CSS/style.css">
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
	<%
		String successMessage = (String) request.getAttribute("successMessage");
		HashMap<String, String> errorsMessage = (HashMap<String, String>) request.getAttribute("errorsMessage");
		String previousEmail = (String)request.getAttribute("previousEmail");
		String previousPassword = (String)request.getAttribute("previousPassword");
		String previousFirstname = (String)request.getAttribute("previousFirstname");
		String previousLastname = (String)request.getAttribute("previousLastname");
	%>

	<h1>Formulaire d'inscription</h1>
	<% if(successMessage != null) { %>
		<div class="successMessage">
			<p><%= successMessage %></p>
		</div>
	<% } %>
	
	<form action="Inscription" method="post">
		<% if(previousEmail == null && previousPassword == null && previousFirstname == null && previousLastname == null) { %>
		
		<p><label for="email">Email </label> : <input type="email" id="email" name="email" placeholder="Email" required="required"/></p>
		<p><label for="password">Mot de passe </label> : <input type="password" id="password" name="password" placeholder="Mot de passe" required="required"/></p>
		<p><label for="lastname">Nom </label> : <input type="text" id="lastname" name="lastname" placeholder="Nom" required="required"/></p>
		<p><label for="firstname">Prénom</label> : <input type="text" id="firstname" name="firstname" placeholder="Prénom" required="required"/></p>
		
		<% } else { %>
		
		<p><label for="email">Email </label> : <input type="email" id="email" name="email" placeholder="Email" required="required" value="<%= previousEmail %>"/></p>
		<p><label for="password">Mot de passe </label> : <input type="password" id="password" name="password" placeholder="Mot de passe" required="required" value="<%= previousPassword %>"/></p>
		<p><label for="lastname">Nom </label> : <input type="text" id="lastname" name="lastname" placeholder="Nom" required="required" value="<%= previousLastname %>"/></p>
		<p><label for="firstname">Prénom</label> : <input type="text" id="firstname" name="firstname" placeholder="Prénom" required="required" value="<%= previousFirstname %>"/></p>
		
		<% } %>
		<input type="submit" value="Confirmer" class="btnConfirm"/>
		<a href="Index"><input type="button" value="Annuler" class="btnCancel"/></a>
	</form>
	<div class="errorMessage">
		<%
		if (errorsMessage != null) {
			for (Map.Entry<String, String> error : errorsMessage.entrySet()) {
		%>
			<p><%=error.getValue()%></p>
		<%
			}
		}
		%>
	</div>
</body>
</html>