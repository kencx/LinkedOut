<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<style>
	
	.edit-container {
		grid-area: feed;
		width: 90%;
		text-align: center;
	    margin-top: 50px;    	
	}
	
	.edit-button {
		text-align: center;
	}

	.details {
		margin: 10px;
		padding: 10px;
	}

	.details label {
		float:left;
		margin: 10px;
        text-decoration: bold;
	}

	.details input {
		width: 94%;
		padding: 10px;
	}

</style>

<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
    <link href='https://fonts.googleapis.com/css?family=Roboto Mono' rel='stylesheet'>
	<title>Change Password</title>
</head>

<body>
	<jsp:include page="navbar.jsp"/>

	<div class="feed-main">
		
		<div class="container edit-container">
			<h1>Change Password</h1>
			<p>Fill in the following fields.</p>
			
			<c:set value='${requestScope.message}' var="message"/>
			<c:if test='${message != null}'>
				<p class="error-message"><c:out value='${message}'/></p>
			</c:if>
					
			<form class="details" method="POST" action="changepassword">

				<label for="currentPassword">Current Password*</label>
				<input type="password" placeholder="Current Password" name="currentPassword" required/>
				<br> 
				
				<label for="Password">Password*</label>
				<input type="password" placeholder="New Password" name="newPassword" required/>
				<br> 
				
				<label for="Password">Confirm Password*</label>
				<input type="password" placeholder="Confirm Password" name="confirmPassword" required/>
				<br>
                   <p></p>
				
				<div class="edit-button">
					<button class="post-button" type="submit">Save</button>
					<button class="post-button" type="button">Cancel</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>