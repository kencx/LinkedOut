<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>	

</style>

<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/navbar.css" />
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
	<title>Insert title here</title>
</head>

<body>

	<nav class="scroll-item">
	
		<div class="nav-items">
			<a href="homefeed"><i class="fa fa-home"></i> LinkedOut</a>
			<a href="profile" title="Profile"><i class="fa fa-user"></i></a>
			<a href="#" title="Messages"><i class="fa fa-envelope"></i></a>
			<a href="#" title="Notifications"><i class="fa fa-bell"></i></a>
			<a href="logout" title="Logout"><i class="fa fa-power-off"></i></a>

			<form class="search-bar" action="search" method="GET">
				<button type="submit">
					<i class="fa fa-search"></i>
				</button>
				<input type="text" placeholder="Search..." name="searchbar">
			</form>
		</div>
	</nav>

</body>
</html>