<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1"> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/details.css" /> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
	<title>Login Page</title>
</head>

<body>
	<jsp:include page="indexbar.jsp"/>
	
	<jsp:include page="logincard.jsp"/>
</body>
</html>