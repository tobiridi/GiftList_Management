<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/style.css">
<title>Ajout cadeau</title>
</head>
<body>
	<%
		HashMap<String, String> errorsMessage = (HashMap<String, String>) request.getAttribute("errorsMessage");
		String successMessage = (String) request.getAttribute("successMessage");
		String idList = (String) request.getAttribute("idList");
		
		//form parameter
		String giftPriority = (String) request.getAttribute("giftPriority");
		String giftName = (String) request.getAttribute("giftName");
		String giftDescription = (String) request.getAttribute("giftDescription");
		String giftPrice = (String) request.getAttribute("giftPrice");
		
	%>
	
	<jsp:include page="NavBar.jsp">
		<jsp:param value="3" name="pageIndex"/>
	</jsp:include>
	
	<h1>Ajouter un cadeau</h1>
	<% if(successMessage != null) { %>
		<div class="successMessage">
			<p><%= successMessage %></p>
		</div>
	<% } %>
	
	<form action="AddGift" method="post" enctype="multipart/form-data">
	<% if(giftPriority == null && giftName == null && giftDescription == null && giftPrice == null) { %>
		<p><label for="giftPriority">Priorité</label> : <input type="number" id="giftPriority" name="giftPriority" min="1" max="100" value="1"/></p>
		<p><label for="giftName">Nom du cadeau</label> : <input type="text" id="giftName" name="giftName" required="required" /></p>
		<p><label for="giftDescription">Description</label> : <input type="text" id="giftDescription" name="giftDescription" required="required" /></p>
		<p><label for="giftPrice">Prix du cadeau</label> : <input type="text" id="giftPrice" name="giftPrice" placeholder="Exemple: 12.99" required="required" /></p>
		
	<% } else { %>
		<p><label for="giftPriority">Priorité</label> : <input type="number" id="giftPriority" name="giftPriority" min="1" max="100" value="<%= giftPriority %>"/></p>
		<p><label for="giftName">Nom du cadeau</label> : <input type="text" id="giftName" name="giftName" required="required" value="<%= giftName %>"/></p>
		<p><label for="giftDescription">Description</label> : <input type="text" id="giftDescription" name="giftDescription" required="required" value="<%= giftDescription %>"/></p>
		<p><label for="giftPrice">Prix du cadeau</label> : <input type="text" id="giftPrice" name="giftPrice" placeholder="Exemple: 12.99" required="required" value="<%= giftPrice %>"/></p>
	<% } %>
	
		<p><label for="giftLink">Lien (optionel)</label> : <input type="text" id="giftLink" placeholder="Exemple: https://google.com" name="giftLink"/></p>
		<p><label for="giftPicture">Image (optionel)</label> : <input type="file" id="giftPicture" name="giftPicture" accept="image/*"/></p>
		<input type="submit" value="Confirmer" class="btnConfirm"/>
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
	
	<script type="text/javascript">
		let giftPicture = document.getElementById('giftPicture');
		giftPicture.onchange = function () {
			if(this.files[0].size > 2097152){
				alert("Fichier trop gros !\nLe fichier ne doit pas dépasser 2MB.");
				this.value = "";
			};
		};
	</script>
</body>
</html>