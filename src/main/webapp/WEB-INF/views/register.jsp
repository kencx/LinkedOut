<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>

<head>
	<title>Create Account Page</title>
	<link href="${pageContext.request.contextPath}/resources/css/details-card.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<jsp:include page="indexbar.jsp"/>
	
	<div class="card">
		<h1>Create Account</h1>
		
		<form class="details" method="POST" action="register">
			<p class="message">Already have an account? <a href="login">Login here</a></p>
			
			<!-- TODO: add more fields for personal info -->
			
			<c:set value='${requestScope.registerFailedMessage}' var="errorMessage"/>
			<c:if test='${errorMessage != null}'>
				<p class="error-message"><c:out value='${errorMessage}'/></p>
			</c:if>
			
			<input type="text" placeholder="Username" name="username"/>		
			<input type="password" placeholder="Password" name="password"/>
			<input type="password" placeholder="Confirm Password" name="confirmPassword"/>

			<button>create account</button>
		</form>		
	</div>
</body>
</html>