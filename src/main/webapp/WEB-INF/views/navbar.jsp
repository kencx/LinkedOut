<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>

	.nav-items {
		list-style-type: none;
		margin: 0;
		padding: 0;
		overflow: hidden;
		background-color: #333;
	}	
		
	.nav-items a {
		float: left;
		display: block;
		color: white;
		text-align: center;
		padding: 14px 16px;
		text-decoration: none;
	}
		
	.nav-items a:hover {
		background-color: #444;
	}
	
	.nav-items a.active {
		background-color: #888;
	}
	
</style>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

	<nav>
		<div class="home-icon">
			
		</div>
		
		<div class="nav-items">
			<a href="home">Home</a>
			<a href="profile">Profile</a>
			<a href="logout">Logout</a>
		</div>
		
		<div>
			<form action="search" method="GET">
				<input type="search" placeholder="Search..." name="searchbar">
				<button type="submit"></button>
			</form>
		</div>
	</nav>

</body>
</html>