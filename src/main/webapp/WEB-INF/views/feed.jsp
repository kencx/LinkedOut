<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<style>


</style>
<head>
<meta charset="ISO-8859-1">
	
	<!-- stylesheets -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/feed.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto Mono' >
	
	<!-- js -->	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
	<title>Insert title here</title>
</head>

<body>

	<!-- main feed block -->
	<div class="feed">
				
		<!-- post status text box block -->
		<div class="container welcome-container">
			
			<div>
				<div class="welcome-header">
				<c:set value="${requestScope.user}" var="user"></c:set>
					<h3>Welcome ${user.firstname} ${user.lastname}!</h3>
					<button id="option-menu">...</button>
				</div>

				<div>
					<form method="POST" action="homefeed">
						<textarea id="textarea" name="postText" rows="2" placeholder="Something to share...?"></textarea>
					    <input type="text" class="tag-bar" name="tags" placeholder="Tags: tag1, tag2, ..." autocomplete="off" />
					    
						<button class="post-button" style="float: right;" type="submit">Post</button>
				 	 </form>
				 	 <p></p>
				</div>
			</div>
		</div>
		
		<!-- post block -->
		<c:forEach var="post" items="${posts}" varStatus="loop">
			<div class="container">
			
				<div class="post-header">
					<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar"/>
					<a href="profile/${post.user.username}">${post.user.firstname} ${post.user.lastname}</a>
					
					<p id="profile"><c:if test="${post.user.occupation != null}">${post.user.occupation}</c:if> <c:if test="${post.user.location != null}">| ${post.user.location}</c:if></p>
					<button id="option-menu">...</button>
					<p class="time">${post.getTimePassed()}</p>
				</div>
			
				<div class="post-body">
					<p>${post.body}</p>
					<c:if test="${post.printTags().size()>0}">
						<span style="font-size: 12px">Tags: </span>
					</c:if>
					<c:forEach var="tag" items="${post.printTags()}">
						<a href="search/${tag}">#${tag}</a> 
					</c:forEach>
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
								
								<div class="left-buttons">
									<span class="showHideComments" onclick="showHideComments(${loop.index})">${post.comments.size()} comments</span> 
									<c:set value="${post.usersWhoLiked}" var="likedUsers"/> 
									<c:if test="${likedUsers.size() > 0}">
										| <span class="likes" onclick="showLikeContainer(${loop.index})">${likedUsers.size()} like(s)</span>
									</c:if>		
								</div>
							</div>
							
							<!-- modal container for liked users button -->
							<div id="listOfLikedUsers${loop.index}" class="modal" data-keyboard="false" data-backdrop="static">
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
									<button class="post-button" onclick="document.getElementById('listOfLikedUsers${loop.index}').style.display='none'">Close</button>
								</div>
							</div>
							
							<!-- post comment text block -->
							<textarea id="textarea" name="commentText" rows="1" cols="25" placeholder="Post a comment..."></textarea>
							<p></p>
						</form>	
					</div>
				
					<!--  comment block -->
					<div class="post-comments" id="showhide${loop.index}" style="display: none"> 
						<c:forEach var="comment" items="${post.getCommentsSortedByTime()}">
							<div class="container comment-container" style="box-shadow:none;border:1px solid #ccc;margin-left: 10px;">
							
								<div class="comment-header">
									<img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="Avatar" class="avatar" id="comment"/>
									<h5>${comment.user.firstname} ${comment.user.lastname}</h5>
									<p id="profile"><c:if test="${comment.user.occupation != null}">${comment.user.occupation}</c:if> <c:if test="${comment.user.location != null}">| ${comment.user.location}</c:if></p>
									<button id="option-menu">...</button>
									<p class="time">${comment.getTimePassed()}</p>
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

<script>
	// shows correct post's likes modal container
	function showLikeContainer(idx) {
		var x = document.getElementById("listOfLikedUsers"+idx);
		x.style.display = "block";
	}
</script>

<script>
	// shows correct post's comments container
function showHideComments(idx) {
	var x = document.getElementById("showhide"+idx);
	
	if (x.style.display === "none") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}
</script>

</html>