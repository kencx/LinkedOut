<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<style>

	.scroll-card {
		position: fixed;
		z-index: 1;
		width: 300;
		left: 5%;
		right: 75%;
	}
	
	.profile-card {
		width: 100%;
		position: absolute;
		color:#000;
		background-color:#fff;
		padding: 20px 20px;
		margin: 20px 20px;
		border-radius: 4px;
		box-shadow:0 2px 5px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
		
		text-align: center;	
	}
		
	.profile-avatar {
		width: 100px;
		border-radius: 50%;
	}
</style>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<div class="scroll-card">
	
		<div class="profile-card">
			<h3>My Profile</h3>
			<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="profile-avatar"/><br>
			<h3>${requestScope.user.username}</h3>
			<hr style="opacity:0.5">
			<p>Singapore</p>
			<!-- occupation, dob, location -->
		</div>
	</div>
</body>
</html>