<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<style>
	.introduction h1 {
		margin-top: 120px;
		font-size: 96px;
		margin-left: 30px;
	}
	
	.logincard {
		margin-top: -70px;
		margin-right: -1200px;
	}
</style>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
<title>LinkedOut</title>
</head>

<body>
	<jsp:include page="indexbar.jsp"/>
	
	<div class="introduction">
		<h1>Welcome to LinkedOut</h1>
	</div>
	
	<div class="logincard">
		<jsp:include page="logincard.jsp"/>
	</div>
	
</body>
</html>