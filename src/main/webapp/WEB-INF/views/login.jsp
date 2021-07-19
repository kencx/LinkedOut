<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>

	<p>Please enter your username and password to login.</p>

	<form method="POST" action="login">
		<input type="text" name="username"/>
		<input type="password" name="password"/>
		<input type="submit" name="Submit"/>
	</form>
	
</body>
</html>