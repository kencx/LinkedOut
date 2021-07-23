<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<style>

	.introduction {
		float: left;
		width: 40%;
		margin-top: 100px;
		margin-left: 100px;
		padding: 30px;
	}
	
	.introduction h1 {
		font-size: 54px;
	}
	
	.introduction h2 {
		font-size: 32px;
		font-family: "Open Sans";
		margin-left: 60px;
		margin-top: 40px;
	}
	
	.image {
		padding: 30px;
		border: 5px solid #495867;
		text-align: center;
		align-content: center;
	}
	
	.header-image {
		float:right;
		margin-top: 120px;
		margin-right: 160px;
		width: 20%;		
	}
	
	.primary-image {
		min-height: 400px;
		line-height: 50px;
	}
	
	.logincard {
		width: 40%;
		float: right;
	}
		
</style>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
<title>LinkedOut</title>
</head>

<body>
	<jsp:include page="indexbar.jsp"/>
	<div>
		<div class="introduction">
			<h1>Welcome to LinkedOut</h1>
			<h2>Join the community today!</h2>
			<br>	
			<div class="image primary-image">
				<h3>Another Placeholder image</h3>
			</div>
		</div>
		
		<div class="image header-image">
			<h3>Placeholder image here</h3>
		</div>
	</div>
		
	<div class="logincard">
		<jsp:include page="logincard.jsp"/>
	</div>
	
	<div class="footer">
		<p>Kenneth Cheo | © 2021</p>
	</div>
	
</body>
</html>