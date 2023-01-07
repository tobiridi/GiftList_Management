<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="be.Jadoulle_Declercq.JavaBeans.Gift"%>
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
	<%
		boolean isFriendGiftList = false;
		if(request.getAttribute("isFriendGiftList") != null){
			isFriendGiftList = (boolean) request.getAttribute("isFriendGiftList");
		}
	%>
	<jsp:useBean id="listCustomer" class="be.Jadoulle_Declercq.JavaBeans.GiftList" scope="request"></jsp:useBean>
	<jsp:include page="NavBar.jsp">
		<jsp:param value="3" name="pageIndex"/>
	</jsp:include>
	
	<h1>Détails de la liste</h1>
	<%-- table giftList --%>
	<table>
		<thead>
			<tr>
				<th>Type</th>
				<th>Date limite</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%= listCustomer.getType() %></td>
				<td><%= listCustomer.getDeadLine() != null ? listCustomer.getDeadLine().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "aucune date défini" %></td>
			</tr>
		</tbody>
	</table>
	
	<%-- table gifts --%>
	<h2>Liste des cadeaux</h2>
	<hr>
	<% if(!listCustomer.getGifts().isEmpty()) { %>
		<table>
			<thead>
				<tr>
					<th>Priorité</th>
					<th>Nom</th>
					<th>Description</th>
					<th>Prix</th>
					<th>Lien</th>
					<th>Image</th>
					<th>Option</th>
				</tr>
			</thead>
			<tbody>
				<% for(Gift g : listCustomer.getGifts()) { %>
					<tr>
						<td><%= g.getPriority() %></td>
						<td><%= g.getName() %></td>
						<td><%= g.getDescription() %></td>
						<td><%= g.getAveragePrice() %></td>
						<% if(g.getLink() != null) { %>
							<td><input type="button" id="giftLink" name="giftLink" value="Copier le lien" onclick="copyLink('<%= g.getLink() %>')"></td>
						<% } else { %>
							<td>Aucun lien</td>
						<% } %>
						
						<%-- TODO : not finished --%>
						<% if(g.getPicture() != null) { %>
							<td>
								<img src="data:image/png;base64,<%= g.getPicture() %>" width="360" height="360">
							</td>
						<% } else { %>
							<td>Aucune image</td>
						<% } %>
						
						<td>
							<%-- TODO : add modify Gift --%>
							<% if(!isFriendGiftList) { %>
								<a href="#"><input type="button" value="Modifier"/></a>
								<a href="#"><input type="button" value="Supprimer"/></a>
							<% } %>
							<%-- TODO : add modify Gift --%>
							<a href="#"><input type="button" value="Détails"/></a>
						</td>
					</tr>
				<% } %>
			</tbody>
		</table>
	<% } else { %>
		<p style="text-align: center;">Il n'y a aucun cadeaux.</p>
	<% } %>
	<!-- maybe change -->
	<% if(!isFriendGiftList) { %>
		<form action="#" method="get">
			<%-- TODO add add gift button --%>
			<a href="#"><input type="button" value="Ajouter un cadeau" class="btnConfirm"/></a>
		</form>
	<% } %>
	
	<script type="text/javascript">
		function copyLink(link) {
			// Copy the link of the gift
			navigator.clipboard.writeText(link);
			
			alert("Lien copié.");
		}
	</script>
</body>
</html>