<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<style>

	left-container {
        grid-area: left;
        display: flex;
        padding: 10px 10px;
        /* border: 1px solid black; */
    }

    .left-container img {
        align-items: flex-end;
    }

    .left-container .primary-text {
        margin: 0 30px;
    }

    .right-container {
        grid-area: right;
        padding: 50px 50px;
        text-align: center;
        /* border: 1px solid black; */
    }
		
</style>

<head>
<meta charset="ISO-8859-1">
<link href='https://fonts.googleapis.com/css?family=Roboto Mono' rel='stylesheet'>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
<title>LinkedOut</title>
</head>

<body>
	<jsp:include page="indexbar.jsp"/>
	
    <div class="main">

            <div class="left-container">
                <div class="primary-text">
                    <h1 style="font-size: 82px; margin-left: 10px;">LinkedOut</h1>
                    <h1>Igitur ne dolorem quidem.</h1>
                    <h3>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum non sem et lacus malesuada mollis. Praesent malesuada at diam id gravida. Nulla orci arcu, ornare eget fringilla sit amet, hendrerit eu mi.</h3>
                </div>
                <img src="img/stock.png" height=auto width=600px />
            </div>
            
            <div class="right-container">
                <h1>placeholder card</h1>
                	<div class="logincard">
                    	<jsp:include page="logincard.jsp"/>
                    </div>
            </div>

    </div>

    <div class="footer">
        <p>Kenneth Cheo | © 2021</p>
    </div>
	
</body>
</html>