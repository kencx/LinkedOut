<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/feed.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto Mono' >	
	<title>Search</title>
</head>
<body>
	
	<jsp:include page="navbar.jsp"/>
		
	<!-- found post block -->
	<div class="feed" style="margin-top:200px">
		<div class="container post-container">
		<c:set var="searchResult" value="${sessionScope.searchResult}"></c:set>
		
			<c:if test="${sessionScope.searchResult == null}">
				<div>
					<h5>Searching ${searchbar} returned 0 posts.</h5>
				</div>
			</c:if>
			
			<c:forEach var="post" items="${searchResult}">
				
				<div class="post-header">
					<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar"/>
					<h4>${post.user.firstname} ${post.user.lastname}</h4>
					<p id="profile">${post.user.occupation} | ${post.user.location}</p>
					<button id="option-menu">...</button>
					<p class="time">${post.getTimePassed()}</p>
					<p>${post.printTags()}</p> <!-- print tags here -->
				</div>
			
				<div class="post-body">
					<p>${post.body}</p>
				</div>
				
				<hr class="clear"></hr>
				
				<!-- interact buttons, comments footer block -->
				<div class="post-footer">				
					
					<div class="interaction-bar">
						<div>
							<c:set value="${post.usersWhoLiked}" var="likedUsers"/> 
							<c:if test="${likedUsers.size() > 0}">
								<span class="likes">${likedUsers.size()} like(s)</span>
							</c:if>		
						</div>
			
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
</body>
</html>