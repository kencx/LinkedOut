<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<style>
</style>

<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profile.css" />
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto Mono' >
	<title>Insert title here</title>
</head>

<body>
	<div class="sticky scroll-container">
		
		<div class="card container profile-container">
		
			<div>
				<h1><a href="profile">My Profile</a></h1>
				<c:set value="${requestScope.user}" var="user"></c:set>
				<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="profile-avatar"/><br>
				<h3>${user.firstname} ${user.lastname}</h3>
				<hr style="opacity:0.5">
				
				<c:if test="${user.location != null}">
					<p>Location: ${user.location}</p>
				</c:if>
				<c:if test="${user.occupation != null}">
					<p>Occupation: ${user.occupation}</p>
				</c:if>
				<c:if test="${user.bio != null}">
					<p style="text-align:justified;">${user.bio}</p>
				</c:if>
				<p></p>
			</div>
		</div>
	</div>
</body>
</html>