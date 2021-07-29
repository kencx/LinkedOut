<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>	

</style>

<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto Mono' >
	<title>Insert title here</title>
</head>

<body>

	<div class="header sticky navbar">
        <div>
            <a href="homefeed"><i class="fa fa-home"></i> LinkedOut</a>
        
            <form class="search-bar" action="search" method="POST">
				<input type="text" placeholder="Search" name="searchbar" autocomplete="off">
			</form>
        </div>
        <div class="settings">
            <!-- <a href="#"><i class="fa fa-envelope"></i> Messages</a> -->
            <a href="#"><i class="fa fa-bell"></i> Notifications</a>
            <a href="profile">Profile</a>
            <a href="options">Options</a>
            <a href="logout">Logout</a>
        </div>
    </div>

</body>
</html>