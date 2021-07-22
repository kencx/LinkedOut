<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<style>
	.main {
		margin-top: 80px;
	}
	
</style>

<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
</head>
<body>

	<jsp:include page="navbar.jsp"/>
	
	<div class="main">
		<hr style="opacity:0">
		<jsp:include page="profilecard.jsp"/>
		<jsp:include page="feed.jsp"/>
	
	</div>

	
</body>
</html>