<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<style>
	
	.tools {
		position: absolute;
		top: 106px;
		left: 8%;
		align: center;
		padding-top: 12px;
		border: 1px solid #ccc;
		border-radius: 4px;
		background-color: #495867;
	}
	
	#edit-container {
		width: 30%;
	}
	
	#delete-container {
		width: 20%;
	}
	
	.edit-button {
		text-align: center;
	}
	
	label {
		text-decoration: bold;
	}
</style>

<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/details.css" />
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
	<title>Insert title here</title>
</head>

<body>
	<div class="tools">

		<!-- edit particulars button -->
		<button class="post-button"
			onclick="document.getElementById('edit').style.display='block'">Edit</button>

		<div id="edit" class="modal" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-container" id="edit-container">
				<h1>Edit Particulars</h1>
				<p>Fill in the following fields.</p>

				<form class="details" method="POST" action="profile">

					<!-- replace with settings error message
						<c:set value='${requestScope.loginFailedMessage}' var="errorMessage"/>
						<c:if test='${errorMessage != null}'>
							<p class="error-message"><c:out value='${errorMessage}'/></p>
						</c:if>
						 -->

					<c:set var="user" value="${requestScope.user}"></c:set>
						<label for="firstname">First Name</label><br> 
						<input type="text" placeholder="${user.firstname}" name="firstname" />

						<label for="lastname">Last Name</label><br> <input
							type="text" placeholder="${user.lastname}" name="lastname" />

						<label for="Location">Location</label><br> <input type="text"
							placeholder="Location" name="location" />

						<label for="lastname">Occupation</label><br> <input
							type="text" placeholder="Occupation" name="occupation" />

						<label for="Password">Password</label><br> <input
							type="password" placeholder="Password" name="password" />
						<br> 
						<label for="Password">Confirm Password</label><br>
						<input type="password" placeholder="Confirm Password"
							name="confirmPassword" />

					<div class="edit-button">
						<button class="post-button" type="submit">Save</button>
						<button class="post-button"
							onclick="document.getElementById('edit').style.display='none'">Cancel</button>
					</div>
				</form>

			</div>
		</div>


		<!-- delete button -->
		<button class="post-button"
			onclick="document.getElementById('delete').style.display='block'">Delete</button>
		
		<form method="POST" action="profile">
			<div id="delete" class="modal" data-keyboard="false"
				data-backdrop="static">

				<div class="modal-container" id="delete-container">
					<h3>Delete Account?</h3>

					<div class="delete">
						<button class="post-button" type="submit" name="delete" value="delete-profile">Delete</button>
						<button class="post-button" onclick="document.getElementById('delete').style.display='none'">Cancel</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>

<script>
	// Get the modal
	var modal = document.getElementById('edit');
	
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
</script>
</html>