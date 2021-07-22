<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<style>
	.feed {
		position: absolute;
		left: 30%;
		right: 30%;
	}
	
	.post-card, .comment-card {
		color:#000;
		background-color:#fff;
		min-height: 35px;
		padding:0.01em 16px;
		margin: 16px;
		border-radius: 4px;
		box-shadow:0 2px 5px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
	}
	
	.post-text-box {
		color:#000;
		background-color:#fff;
		
		padding: 0 10px;
		margin: 16px;
		border-radius: 4px;
		box-shadow:0 2px 5px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
	}
	
	.post-card:before, .post-card:after, .post-text-box:before, .post-text-box:after {
		content:"";
		display:table;
		clear:both
	}
	
	button {
		border:none;
		display:inline-block;
		min-height: 25px;
		padding:5px 18px;
		margin-left: 10px;
		margin-bottom:16px;
		margin-right: 5px;
		vertical-align:middle;
		overflow:hidden;
		text-decoration:none;
		text-align:center;
		cursor:pointer;
		white-space:nowrap;
	}
	
	button:hover {
		color:#000;
		background-color:#ccc;
	}
	
	textarea {
		width: 92%;
		padding: 10px;
		margin: 0 10px 10px 10px;
		line-height: 1.5;
		border: 1px solid #ccc;
		border-radius: 5px;
		resize: none;
	}
	
	.post-header {
		width: 25%;
	}
	
	.post-body {
		margin-left: 20px;
	}
	
	.avatar {
		float: left;
		width: 40px;
		margin-right: 16px;
		border-radius: 50%;
	}
	
	.avatar#comment {
		width: 20px;
	}
	
	.time {
		font-size: 12px;
		margin-top: -16px;
	}
	
	.interaction-bar button {
		float: left;
	}
	
	.interaction-bar .likes {
		float:right; 
		margin-right:20px
	}
		
	.clear {
		opacity: 0.1;
	}
	
</style>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<div class="feed">
	
		<div class="post-text-box">
			<h3 style="opacity: 0.8; margin: 16px">Welcome ${requestScope.user.username}</h3>
			<form method="POST">
				<textarea name="post-text-box" rows="2" placeholder="Tell us about your day..."></textarea>
				<button type="submit">Post</button>
			</form>	
		</div>
	
		<c:forEach var="post" items="${sessionScope.posts}">
			<div class="post-card">
				<div class="post-header">
					<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar"/>
					<h4>${post.user.username}</h4><p class="time">5s</p>
				<!-- post time -->
				</div>
				
				<hr class="clear" style="margin-top:-5px"></hr>
				
				<div class="post-body">
					<p>${post.body}</p>
				</div>
				
				<hr class="clear"></hr>
				
				<div class="post-footer">					
					<div class="post-comment-box">
						<form method="POST">
							<input type="hidden" value="${post.postId}" name="changedPost">
							
							<div class="interaction-bar">
								<!-- if user liked already, change to unlike button -->
								<button type="submit" name="like-button" value="liked">Like</button>
								<button type="submit">Comment</button>
								
								<div>
									<!-- pop out window showing liked users -->
									<c:set value="${post.usersWhoLiked}" var="likedUsers"/> 
									<c:if test="${likedUsers.size() > 0}">
										<span class="likes"><button>
											${likedUsers.size()} like(s)
										</button></span>
									</c:if>
								</div>
							
							</div>
							<textarea name="comment-text-box" rows="1" cols="25" placeholder="Post a comment..."></textarea>
						</form>	
					</div>
				
					<div class="post-comments">
						<c:forEach var="comment" items="${post.comments}">
							<div class="comment-card">
								<div class="comment-header">	
									<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar" id="comment"/>
									<span style="float:right; font-size:12px">5s</span><h5>${comment.user.username}</h5>
								</div>
								<div class="comment-body">
									<p>${comment.commentBody}</p>
								</div>					
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			
		</c:forEach>
	</div>
</body>
</html>