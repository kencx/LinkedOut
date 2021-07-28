<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
	<link href='https://fonts.googleapis.com/css?family=Roboto Mono' rel='stylesheet'>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
	<title>Insert title here</title>
</head>

<body>

	<div class="form-container">
		<div class="card form-card">
			<h1>Log In</h1>
			
			<form class="form-details" method="POST" action="login">
				<p class="message"><a href="register">Create an account</a></p>
				
				<c:set value='${requestScope.loginFailedMessage}' var="errorMessage"/>
				<c:if test='${errorMessage != null}'>
					<p class="error-message"><c:out value='${errorMessage}'/></p>
				</c:if>	
				
				<input type="text" placeholder="Username" name="username" required />
				<input type="password" placeholder="Password" name="password" required/>
				<button>login</button>
				
				<p class="message" align="right"><a href="recovery">Forget password?</a></p>
			</form>
		</div>
	</div>
</body>
</html>