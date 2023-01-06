<%
	int pageIndex = Integer.valueOf((String) request.getParameter("pageIndex"));
	boolean hasUnreadMessage = request.getParameter("hasUnreadMessage") != null ? true : false;
%>
<jsp:useBean id="customerLog" class="be.Jadoulle_Declercq.JavaBeans.Customer" scope="session"></jsp:useBean>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<div class="navigation-container">
	<div class="navigation">
		<ul>
			<li class="list">
				<a href="MainPage">
					<span class="navigation-icon">
						<ion-icon name="home-outline"></ion-icon>
					</span>
					<span class="navigation-text">Accueil</span>
				</a>
			</li>
			<li class="list">
				<%-- TODO : url --%>
				<a href="#">
					<span class="navigation-icon">
						<ion-icon name="person-add-outline"></ion-icon>
					</span>
					<span class="navigation-text">Ajouter un ami</span>
				</a>
			</li>
			<li class="list">
				<a href="GiftList">
					<span class="navigation-icon">
						<ion-icon name="list-outline"></ion-icon>
					</span>
					<span class="navigation-text">Liste de cadeaux</span>
				</a>
			</li>
			<li class="list">
				<a href="NotificationMessage">
					<span class="navigation-icon">
						<% if(customerLog.hasUnreadMessage()) { %>
							<ion-icon name="mail-unread-outline"></ion-icon>
						<% } else { %>
							<ion-icon name="mail-outline"></ion-icon>						
						<% } %>
					</span>
					<span class="navigation-text">Messages</span>
				</a>
			</li>
		</ul>
	</div>
	
	<%-- icon pour les messages --%>
	<!-- <ion-icon name="mail-outline"></ion-icon> -->
	<!-- <ion-icon name="mail-open-outline"></ion-icon> -->
	<!-- <ion-icon name="mail-unread-outline"></ion-icon> -->	
</div>

<%-- change nav bar position depends index (pageIndex) --%>
<% if(pageIndex > 0) { %>
	<script>
		let list = document.querySelectorAll('.list');
		list[<%= pageIndex -1 %>].classList.add('active');
	</script>	
<% } %>