<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1"> 
	<link href='https://fonts.googleapis.com/css?family=Roboto Mono' rel='stylesheet'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
	<title>Login Page</title>
</head>

<body>
	<jsp:include page="indexbar.jsp"/>

	<jsp:include page="logincard.jsp"/>
	
	<div class="footer">
		<p>Kenneth Cheo | © 2021</p>
	</div>
</body>
</html>