<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>

<head>
	<title>Create Account Page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/details.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
</head>

<body>
	<jsp:include page="indexbar.jsp"/>
	
	<div class="card details-container">
		<h1>Create Account</h1>
		
		<form class="details" method="POST" action="register">
			<p class="message">Already have an account? <a href="login">Login here</a></p>
						
			<c:set value='${requestScope.registerFailedMessage}' var="errorMessage"/>
			<c:if test='${errorMessage != null}'>
				<p class="error-message"><c:out value='${errorMessage}'/></p>
			</c:if>
			
			<input type="text" placeholder="First Name" name="firstname" required/>		
			<input type="text" placeholder="Last Name" name="lastname" required/>		
			
			<input type="text" placeholder="Username" name="username" 
				required pattern="^[A-Za-z0-9]{5,}$"
				oninvalid="this.setCustomValidity('Username must be more than 5 characters')" oninput="this.setCustomValidity('')"
			/>		
			
			<input type="password" placeholder="Password" name="password"
				required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.]).{8,}$"
				oninvalid="this.setCustomValidity('Password must be at least 8 characters with at least 1 capital letter, number and !@#$%^&*')" oninput="this.setCustomValidity('')"
			/>
			<input type="password" placeholder="Confirm Password" name="confirmPassword" 
				required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.]).{8,}$"
				oninvalid="this.setCustomValidity('Password must be at least 8 characters with at least 1 capital letter, number and !@#$%^&*')" oninput="this.setCustomValidity('')"
			/>

			<button>create account</button>
		</form>		
	</div>
	
	<div class="footer">
		<p>Kenneth Cheo | © 2021</p>
	</div>
</body>
</html>