<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<style>

	.main {
		margin-top: 80px;
	}
	
	.tools {
		position: absolute;
		top: 450px;
		left: 10%;
		right: 70%;
	}
	
	/* button {
	  background-color: #04AA6D;
	  color: white;
	  padding: 14px 20px;
	  margin: 8px 0;
	  border: none;
	  cursor: pointer;
	  width: 100%;
	  opacity: 0.9;
	}

	button:hover {
	  opacity:1;
	} */
	
	.modal {
		display: none;
		position: fixed;
		z-index: 1;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		padding-top: 50px;
		overflow: auto;
		opacity: 0.95;
		background-color: #474e5d;
	}
	
	.container {
		position: relative; 
		background-color: #fefefe;
		
		padding: 20px; 
		margin: auto; 
		width: 75%;  
	}
	
	
	
</style>

<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>

<body>
	<jsp:include page="navbar.jsp"/>
	
	<div class="main">
		<hr style="opacity:0">
		<jsp:include page="profilecard.jsp"/>
	
		<div class="tools">
		
			<!-- edit particulars button -->
			<button onclick="document.getElementById('edit').style.display='block'">Edit</button>	
		
			<div id="edit" class="modal" data-keyboard="false" data-backdrop="static">
				<span onclick="document.getElementById('edit').style.display='none'">&times;</span>
				<div class="container">
					<h3>Edit Particulars</h3>
					<p>Fill in the following fields</p>
					
					<div class="save">
						<button type="button">Save</button>
						<button type="button">Cancel</button>
					</div>
				</div>
			</div>
			
			<!-- share button -->
			<button>Share</button>
			
			<!-- delete button -->
			<button onclick="document.getElementById('delete').style.display='block'">Delete</button>	
		
			<div id="delete" class="modal" data-keyboard="false" data-backdrop="static">
				<span onclick="document.getElementById('delete').style.display='none'">&times;</span>
				<div class="container">
					<h3>Delete Account?</h3>
					
					<div class="delete">
						<button type="button">Delete</button>
						<button type="button">Cancel</button>
					</div>
				</div>
			</div>
		</div>
		
		<jsp:include page="feed.jsp"/>
	
	</div>
</body>

<script>
	// Get the modal
	var modal = document.getElementById('edit');
	
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
</script>

</html>