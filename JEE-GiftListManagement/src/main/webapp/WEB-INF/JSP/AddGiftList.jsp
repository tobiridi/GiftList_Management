<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/style.css">
<title>Ajout liste de cadeau</title>
</head>
<body>
	<%
		HashMap<String, String> errorsMessage = (HashMap<String, String>) request.getAttribute("errorsMessage");
		String successMessage = (String) request.getAttribute("successMessage");
		String previousGiftListType = (String) request.getAttribute("previousGiftListType");
		String previousGiftListDeadLine = (String) request.getAttribute("previousGiftListDeadLine");
	%>
	
	<jsp:include page="NavBar.jsp">
		<jsp:param value="3" name="pageIndex"/>
	</jsp:include>
	
	<h1>Ajouter une liste de cadeau</h1>
	<% if(successMessage != null) { %>
		<div class="successMessage">
			<p><%= successMessage %></p>
		</div>
	<% } %>
	
	<form action="GiftList" method="post">
	<% if(previousGiftListType == null && previousGiftListDeadLine == null) { %>
	
		<p><label for="giftListType">Type de liste</label> : <input type="text" id="giftListType" name="giftListType" placeholder="Exemple: Noël, Mariage, ..." required="required" /></p>
		<p><label for="giftListDeadLine">Date limite (optionel)</label> : <input type="date" id="giftListDeadLine" name="giftListDeadLine"/></p>
		
	<% } else { %>
	
		<p><label for="giftListType">Type de liste</label> : <input type="text" id="giftListType" name="giftListType" placeholder="Exemple: Noël, Mariage, ..." required="required" value="<%= previousGiftListType %>"/></p>
		<p><label for="giftListDeadLine">Date limite (optionel)</label> : <input type="date" id="giftListDeadLine" name="giftListDeadLine" value="<%= previousGiftListDeadLine %>"/></p>
		
	<% } %>
	
		<input type="submit" value="Confirmer" class="btnConfirm"/>
		<a href="MainPage"><input type="button" value="Annuler" class="btnCancel"/></a>
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