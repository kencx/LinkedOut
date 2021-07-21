<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<style>

	textarea {
		padding: 10px;
		line-height: 1.5;
		border: 1px solid #ccc;
		border-radius: 5px;
		box-shadow: 1px 1px 1px #999;
	}
	
</style>

<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
</head>
<body>

	<jsp:include page="navbar.jsp"/>
	
	<h1>Welcome ${requestScope.user.username}</h1>
	<h3>You have logged in successfully!</h3>


	<div class="post-text-box">
		<h3>Make a post...</h3>
		<form method="POST" action="home">
			<textarea name="post-text-box" rows="5" cols="50" placeholder="Tell us about your day..."></textarea>
			<button type="submit">Post</button>
		</form>	
	</div>
	
	<c:forEach var="post" items="${sessionScope.posts}">
		<div class="post-card">
			<div class="post-header">
			<!-- user avatar -->
				<h3>${post.user.username}</h3> <!-- make into link -->
			<!-- post time -->
			</div>
			<div class="post-body">
				<p>${post.body}</p>
			</div>
			
			<div class="post-comments">
				<c:forEach var="comment" items="">
					<div class="comment-card">
						<div class="comment-header">	
						<!-- time, name, avatar -->
						</div>
						<div class="comment-body">
						<!-- body -->
						</div>
						<div class="comment-footer">
						<!-- like, comment, date button -->
						<!-- pop out window showing liked users -->
						</div>						
					</div>
				</c:forEach>
				
				<div class="post-comment-form">
					<!-- text box for commenting on each post -->
				</div>
			</div>
		</div>
	</c:forEach>
</body>
</html>