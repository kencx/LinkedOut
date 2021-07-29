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
		display: grid;
		grid-template-columns: 1fr 1fr;
		grid-template-areas: "left-col right-col";

		column-gap: 20px;
		margin: 10px;
		padding: 10px;
	}

	.details label {
		float:left;
		margin: 10px;
		text-decoration: bold;
	}

	.left-column {
		grid-area: left-col;
	}

	.right-column {
		grid-area: right-col;
	}

	.left-column input {
		width: 85%;
		padding: 10px;
	}

	.right-column input {
		width: 85%;
		padding: 10px;
	}

	.full-column {
		grid-column: 1/3;
	}

	.full-column input {
		width: 94%;
		padding: 10px;
	}

</style>

<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
    <link href='https://fonts.googleapis.com/css?family=Roboto Mono' rel='stylesheet'>
	<title>Edit Particulars</title>
</head>

<body>
	<jsp:include page="navbar.jsp"/>
	
	<div class="feed-main">
		
		<div class="container edit-container">
			<h1>Edit Particulars</h1>
			<p>Fill in the following fields.</p>
		
			<c:set value='${requestScope.message}' var="message"/>
			<c:if test='${message != null}'>
				<p class="error-message"><c:out value='${message}'/></p>
			</c:if>
					
			<form class="details" method="POST" action="editparticulars">

				<c:set var="user" value="${requestScope.user}"></c:set>
				<div class="left-column">
					<label for="firstname">First Name</label><br>
					<input type="text" placeholder="${user.firstname}" name="firstname" autocomplete="off"/>
				</div>

				<div class="right-column">
					<label for="lastname">Last Name</label><br>
					<input type="text" placeholder="${user.lastname}" name="lastname" autocomplete="off"/>
				</div>
					
				<div class="full-column">
					<label for="Location">Location</label><br>
					<input type="text" placeholder="Location" name="location" autocomplete="off"/>
					<br>
					
					<label for="lastname">Occupation</label><br>
					<input type="text" placeholder="Occupation" name="occupation" autocomplete="off"/>
					<br>
										
					<label for="Bio">Bio</label><br>
					<textarea placeholder="Write a short bio about yourself." name="bio"></textarea>
					
					<p></p>

					<div class="edit-button">
						<button class="post-button" type="submit">Save</button>
						<button class="post-button" type="button">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>