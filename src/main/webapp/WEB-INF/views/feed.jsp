<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/feed.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
	<link href='https://fonts.googleapis.com/css?family=Roboto Mono' rel='stylesheet'>
	
	<title>Insert title here</title>
</head>

<body>

	<!-- main feed block -->
	<div class="feed">
				
		<!-- post status text box block -->
		<div class="container welcome-container">

			<!-- <div class="color-margin" id="text-box">	
				<p></p>
			</div>  -->	
			
			<div>
				<div class="welcome-header">
					<h3>Welcome Lorem Ipsum!</h3>
					<button id="option-menu">...</button>
				</div>

				<div>
					<form method="POST" action="homefeed">
						<textarea id="textarea" name="postText" rows="2" placeholder="Something to share...?"></textarea>
						<button class="post-button" type="submit">Post</button>
						<!-- accept tag input -->
					</form>	
				</div>
			</div>
		</div>
		
		<!-- post block -->
		<c:forEach var="post" items="${posts}">
			<div class="container post-container">
				
				<div class="post-header">
					<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar"/>
					<h4>${post.user.firstname} ${post.user.lastname}</h4>
					<p id="profile">${post.user.occupation} | ${post.user.location}</p>
					<button id="option-menu">...</button>
					<p class="time">${post.getTimePassed()}</p>
				</div>
			
				<div class="post-body">
					<p>${post.body}</p>
				</div>
				
				<hr class="clear"></hr>
				
				<!-- interact buttons, comments footer block -->
				<div class="post-footer">				
					
					<div class="interaction-bar">
						<form method="POST">
						
							<!-- Save modified post Id -->
							<input type="hidden" value="${post.postId}" name="modifiedPostId">
							
							<div class="bar-buttons">
								<div>
									<!-- TODO: if user liked already, change to unlike button -->
									<button class="post-button" type="submit" name="likeButton" value="clicked">Like</button>
									<button class="post-button" type="submit" name="commentButton" value="clicked">Comment</button>
								</div>

								<div>
									<c:set value="${post.usersWhoLiked}" var="likedUsers"/> 
									<c:if test="${likedUsers.size() > 0}">
									<span class="likes" onclick="document.getElementById('listOfLikedUsers').style.display='block'">${likedUsers.size()} like(s)</span>
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
							<textarea name="commentText" rows="1" cols="25" placeholder="Post a comment..."></textarea>
						</form>	
					</div>
				
					<!--  comment block -->
					<div class="post-comments">
						<c:forEach var="comment" items="${post.getCommentsSortedByTime()}">
							<div class="container comment-container" style="box-shadow:none;border:1px solid #ccc;margin-left: 10px;">
							
								<div class="comment-header">
									<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar" id="comment"/>
									<h5>${comment.user.firstname} ${comment.user.lastname}</h5>
									<p id="profile">${post.user.occupation} | ${post.user.location}</p>
									<button id="option-menu">...</button>
									<p class="time">${comment.getTimePassed()}</p>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit-textarea.js"></script>  
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