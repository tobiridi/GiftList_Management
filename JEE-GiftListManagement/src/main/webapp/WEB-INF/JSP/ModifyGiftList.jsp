<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/style.css">
<title>Modification de la liste</title>
</head>
<body>
	<%
		HashMap<String, String> errorsMessage = (HashMap<String, String>) request.getAttribute("errorsMessage");
	%>
	
	<jsp:useBean id="modifyList" class="be.Jadoulle_Declercq.JavaBeans.GiftList" scope="session"></jsp:useBean>
	
	<jsp:include page="NavBar.jsp">
		<jsp:param value="3" name="pageIndex"/>
	</jsp:include>
	
	<h1>Modification de la liste</h1>
	
	<form action="ModifyGiftList" method="post">
		<p><label for="giftListType">Type de liste</label> : <input type="text" id="giftListType" name="giftListType" placeholder="Exemple: Noël, Mariage, ..." required="required" value="<%= modifyList.getType() %>"/></p>
		
		<% if(modifyList.getDeadLine() != null) { %>
			<p><label for="giftListDeadLine">Date limite</label> : <input type="date" id="giftListDeadLine" name="giftListDeadLine" required="required" value="<%= modifyList.getDeadLine().toString() %>"/></p>
		<% } else { %>
			<input type="checkbox" id="activation" name="activation" checked="<%= modifyList.isActive() %>"/>
      		<label for="activation">activation</label>
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