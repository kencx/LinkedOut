<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>

<head>
	<title>Create Account Page</title>
	<link href='https://fonts.googleapis.com/css?family=Roboto Mono' rel='stylesheet'>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
</head>

<body>
	<jsp:include page="indexbar.jsp"/>
	
	<div class="form-container">
		<div class="card form-card">
			<h1>Create Account</h1>
			
			<form class="form-details" method="POST" action="register">
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
	</div>

	<div class="footer">
        <p>Kenneth Cheo | © 2021</p>
    </div>
    
</body>
</html>