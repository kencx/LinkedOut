<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<style>
    .options-container {
		grid-area: feed;
        justify-self: center;
		width: 50%;
		text-align: center;
	    margin-top: 50px;
	}

    .options-container a {
        font-size: 14px;
    }

    .options-container a:hover {
        color: var(--hover-button-color); 
    }
</style>

<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
    <link href='https://fonts.googleapis.com/css?family=Roboto Mono' rel='stylesheet'>
	<title>Options</title>
</head>

<body>
	<jsp:include page="navbar.jsp"/>

	<div class="feed-main">
		<div class="container options-container">
                <h1>Options</h1>
                <a href="editparticulars">Edit Profile</a><br>
                <a href="changepassword">Change Password</a><br>
                <a href="options">Delete Account</a>
            <p></p>
		</div>
	</div>
</body>
</html>