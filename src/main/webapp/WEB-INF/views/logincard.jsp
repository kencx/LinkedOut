<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/details.css" /> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
	<title>Insert title here</title>
</head>

<body>

	<div class="card details-container">
		<h1>Log In</h1>
		
		<form class="details" method="POST" action="login">
			<p class="message"><a href="register">Create an account</a></p>
			
			<c:set value='${requestScope.loginFailedMessage}' var="errorMessage"/>
			<c:if test='${errorMessage != null}'>
				<p class="error-message"><c:out value='${errorMessage}'/></p>
			</c:if>
		
			<input type="text" placeholder="Username" name="username"/>
			<input type="password" placeholder="Password" name="password"/>
			<button>login</button>
			
			<p class="message" align="right"><a href="recovery">Forget password?</a></p>
		</form>
	</div>
</body>
</html>