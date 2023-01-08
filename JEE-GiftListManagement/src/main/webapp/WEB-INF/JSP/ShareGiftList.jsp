<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="be.Jadoulle_Declercq.JavaBeans.Customer"%>
<%@ page import="be.Jadoulle_Declercq.JavaBeans.GiftList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/style.css">
<title>Partager une liste</title>
</head>
<body>
	<%!
		private boolean customerHasGiftToShare(Customer customer) {
			return customer.getGiftList().stream().filter(gl -> !gl.isExpired()).count() > 0;
		}
	%>

	<%
		HashMap<String, String> errorsMessage = (HashMap<String, String>) request.getAttribute("errorsMessage");
		String successMessage = (String) request.getAttribute("successMessage");
		ArrayList<Customer> allCustomers = (ArrayList<Customer>) request.getAttribute("allCustomers"); 
	%>
	
	<jsp:useBean id="customerLog" class="be.Jadoulle_Declercq.JavaBeans.Customer" scope="session"></jsp:useBean>
	
	<jsp:include page="NavBar.jsp">
		<jsp:param value="2" name="pageIndex"/>
	</jsp:include>
	
	<h1>Partager une liste de cadeau</h1>
	<% if(successMessage != null) { %>
		<div class="successMessage">
			<p><%= successMessage %></p>
		</div>
	<% } %>
	
	<% if(customerHasGiftToShare(customerLog)) { %>
		<form action="ShareGiftList" method="post">
			<p>
				<label>Personne à inviter</label> <select name="customer" required="required">
					<% for(Customer c : allCustomers) { %>
						<option value="<%= c.getId() %>"><%= c.getLastname() + " " + c.getFirstname() %></option>
					<% } %>
				</select>
			</p>
			
			<p>
				<label>Liste de cadeau</label> <select name="giftList" required="required">
					<% for(GiftList list : customerLog.getGiftList()) { %>
						<% if(!list.isExpired()) { %>
							<option value="<%= list.getId() %>">
			 					<%= list.getType() %>
			 				</option>
			 			<% } %>
					<% } %>
				</select>
			</p>
			
			<input type="submit" value="Confirmer" class="btnConfirm"/>
			<a href="MainPage"><input type="button" value="Annuler" class="btnCancel"/></a>
		</form>
	<% } else { %>
		<p style="text-align: center;">Vous n'avez aucune liste qui est actif !</p>
	<% } %>
	
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