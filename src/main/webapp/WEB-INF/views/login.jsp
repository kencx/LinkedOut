<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1"> 
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/details-card.css" /> 
	<title>Login Page</title>
</head>

<body>
	<jsp:include page="navbar.jsp"/>
	
	<div class="card">
		<h1>Log In</h1>
		
		<form class="details" method="POST" action="login">
			<p class="message"><a href="register">Create an account</a></p>
			
			<c:set value='<%= request.getAttribute("loginFailedMessage")%>' var="errorMessage"/>
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