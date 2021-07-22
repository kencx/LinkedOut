<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<style>

</style>

<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" />
	<title>LinkedOut</title>
</head>
<body>

	<jsp:include page="navbar.jsp"/>
	
	<div class="main">
		<hr style="opacity:0">
		<jsp:include page="profilecard.jsp"/>
		<jsp:include page="feed.jsp"/>
	</div>
	
	<button onclick="topFunction()" id="top-button" class="top-button" title="Go to Top"><i class="fa fa-arrow-up"></i></button>

</body>

<script>
	//Get the button:
	mybutton = document.getElementById("top-button");
	
	// When the user scrolls down 20px from the top of the document, show the button
	window.onscroll = function() {scrollFunction()};
	
	function scrollFunction() {
	  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
	    mybutton.style.display = "block";
	  } else {
	    mybutton.style.display = "none";
	  }
	}
	
	// When the user clicks on the button, scroll to the top of the document
	function topFunction() {
	  document.body.scrollTop = 0; // For Safari
	  document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
	}
</script>
</html>