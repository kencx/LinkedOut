<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<style>

	.scroll-container {
		width: 300;
		left: 5%;
		right: 75%;
		top: 18%;
	}
	
	.profile-container {
		width: 100%;
		position: absolute;
		margin: 20px 20px;
	}
		
	.profile-avatar {
		width: 100px;
		border-radius: 50%;
	}
	
	.profile-container {
		text-transform: capitalize;
	}
	
	.container a {
		text-decoration: none;
		color: black;	
	}
	
	.container a:hover {
		text-decoration: underline;
		color: #5691C8;
	}
	
	.profile-container a {
		font-size: 32px;
	}

</style>

<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
	<title>Insert title here</title>
</head>

<body>
	<div class="scroll-item scroll-container">
		
		<div class="card container profile-container">
			<div class="header">
				<h1></h1>
			</div>
			
			<div style="margin: 20px 20px">
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
			</div>
		</div>
	</div>
</body>
</html>