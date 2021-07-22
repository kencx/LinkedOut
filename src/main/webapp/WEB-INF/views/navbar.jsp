<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>	
	nav {
		position: fixed;
		top: 0;
		left: 0;
		z-index: 1;
		width: 100%;
	}
	
	.nav-items {
		list-style-type: none;
		margin: 0;
		padding: 0;
		overflow: hidden;
		background-color: #333;
		white-space: normal;
	}
	
	.nav-items a {
		float: left;
		display: block;
		color: white;
		font-size: 22px;
		text-align: center;
		padding: 16px 16px;
		text-decoration: none;
	}
	
	.nav-items a:hover {
		background-color: #444;
	}
	
	.nav-items a.active {
		background-color: #888;
	}
	
	form.search-bar {
		float: right;
		max-width: 500px;
		margin-top: 10px;
		margin-right: 26px;
	}
	
	form.search-bar input[type=text] {
		padding: 10px;
		float: left;
		border: none;
		font-size: 16px;
	}
	
	form.search-bar button {
		float: right;
		padding: 10px;
		background: #2196F3;
		color: white;
		font-size: 14px;
		border: 1px solid grey;
		border-left: none;
		cursor: pointer;
	}
	
	form.search-bar::after {
		content: "";
		clear: both;
		display: table;
	}
</style>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Insert title here</title>
</head>

<body>

	<nav>
		<div class="home-icon"></div>

		<div class="nav-items">
			<a href="homefeed">Home</a> 
			<a href="profile">Profile</a> 
			<a href="logout">Logout</a>

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