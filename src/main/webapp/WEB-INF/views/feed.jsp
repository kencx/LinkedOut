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
		min-height: 35px;
		padding:0.01em 16px;
		margin: 16px;
	}
	
	.post-text-box {
		margin: 16px;
		padding: padding: 0 10px;
		color:#000;
		background-color:#fff;
		border-radius: 4px;
		box-shadow:0 2px 5px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
	}
	
	.post-card:before, .post-card:after, .post-text-box:before, .post-text-box:after {
		content:"";
		display:table;
		clear:both
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
		width: 25px;
	}
	
	.time {
		font-size: 10px;
		margin-top: -16px;
	}
	
	.interaction-bar .post-button {
		float: left;
	}
	
	.interaction-bar .likes {
		float:right; 
		margin-bottom: -10px;
		margin-right:20px
	}
	
	.likes {
		vertical-align:middle;
		text-align:center;
		cursor:pointer;
	}
	
	.likes:hover {
		text-decoration: underline;
	}
	
	.like-user-card {
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	
	#like-modal-container {
		width: 20%;
	}
	
	.clear {
		opacity: 0.1;
	}
	
	.header #text-box {
		padding: 10px;
	}
		
	.greeting h3 {
		float: left;
		opacity: 0.8; 
		margin: 16px;
		padding-left: 3px; 
		padding-top:5px; 
		text-transform:capitalize;
	}
	
	#options-menu {
		float: right;
		border-radius: 30%;
		padding: 5px;
		margin: 10px;
	}
	
	#options-menu:hover {
		background-color: #EBEBFF;
	}
	
</style>

<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
	<title>Insert title here</title>
</head>

<body>

	<!-- main feed block -->
	<div class="feed">
				
		<!-- post status text box block -->
		<div class="post-text-box">
			<div class="header" id="text-box">	
				<p></p>
			</div>	
			<div class="text-box">
				<div class="greeting">
					<h3>Welcome ${requestScope.user.firstname} ${requestScope.user.lastname}!</h3>
					<span id="options-menu">...</span>
				</div>
				<div>
					<form method="POST">
						<textarea name="post-text-box" rows="2" placeholder="Tell us about your day..."></textarea>
						<button class="post-button" type="submit">Post</button>
					</form>	
				</div>
			</div>
		</div>
		
		<!-- post block -->
		<c:forEach var="post" items="${sessionScope.posts}">
			<div class="container post-card">
				
				<div class="post-header">
					<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar"/>
					<h4>${post.user.firstname} ${post.user.lastname}</h4>
					<p class="time">1 m</p> <!-- post time -->
					<span id="options-menu" style="float:right; margin-top: -50px">...</span>
				</div>
				
				<hr class="clear" style="margin-top:-5px"></hr>
				
				<div class="post-body">
					<p>${post.body}</p>
				</div>
				
				<hr class="clear"></hr>
				
				<!-- interact buttons, comments footer block -->
				<div class="post-footer">				
					
					<div class="post-comment-box">
						<form method="POST">
							<input type="hidden" value="${post.postId}" name="changedPost">
							
							<div class="interaction-bar">
								
								<!-- if user liked already, change to unlike button -->
								<button class="post-button" type="submit" name="like-button" value="liked">Like</button>
								<button class="post-button" type="submit" name="comment-button">Comment</button>

								<div>
									<c:set value="${post.usersWhoLiked}" var="likedUsers"/> 
									<c:if test="${likedUsers.size() > 0}">
									<span class="likes post-button" onclick="document.getElementById('listOfLikedUsers').style.display='block'">${likedUsers.size()} like(s)</span>
									</c:if>		
								</div>
							</div>
							
							<!-- modal container for liked users button -->
							<div id="listOfLikedUsers" class="modal" data-keyboard="false" data-backdrop="static">
								<div class="modal-container" id="like-modal-container">
									<h3>Liked Users</h3>
									
									<!-- list of liked Users -->
									<div class="liked-list">
										<c:forEach var="likedUser" items="${likedUsers}">
											<div class="like-user-card">
												<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar" id="liked-user"/>
												<h4>${likedUser.firstname} ${likedUser.lastname}</h4>
											</div>
										</c:forEach>
									</div>
									<p></p>
									<button class="post-button" onclick="document.getElementById('listOfLikedUsers').style.display='none'">Close</button>
								</div>
							</div>
							
							<!-- post comment text block -->
							<textarea name="comment-text-box" rows="1" cols="25" placeholder="Post a comment..."></textarea>
						</form>	
					</div>
				
					<!--  comment block -->
					<div class="post-comments">
						<c:forEach var="comment" items="${post.comments}">
							<div class="container comment-card" style="box-shadow:none; border: 1px solid #ccc">
								<div class="comment-header">	
									<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar" id="comment"/>
									<p class="time" style="float:right; margin-top:-5px">1 m</p><h5>${comment.user.firstname} ${comment.user.lastname}</h5>
								</div>
								<div class="comment-body">
									<p>${comment.commentBody}</p>
								</div>					
							</div>
						</c:forEach>
					</div>
					
					<!-- limit number of posts on page -->
				</div>
			</div>
		</c:forEach>
	</div>
	
	
	
</body>


<script>
	// Get the modal
	var modal = document.getElementById('listOfLikedUsers');
	
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
</script>
</html>