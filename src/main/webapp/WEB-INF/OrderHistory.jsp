<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.OrderDetailBean" %>
<%@ page import="java.util.Date" %>
<%
	String userId = (String)session.getAttribute("userId");
	String cartId = (String)session.getAttribute("cartId");
	if(userId == null || cartId == null) {
		response.sendRedirect("index.jsp");
		return;
	}
	String message = (String)session.getAttribute("message");
	String orderDetailHistory = (String)session.getAttribute("orderHistories");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>注文履歴</title>
	<link rel="stylesheet" type="text/css" href="css/OrderHistory.css">
	<link rel="stylesheet" href="css/global.css" />
	<link rel="stylesheet" href="css/header.css" />
    <link rel="stylesheet" href="css/topScrollButton.css" />
	<link href="https://use.fontawesome.com/releases/v6.2.0/css/all.css" rel="stylesheet"/>
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css"
    />
	<link rel='icon' href='images/favicon.ico' />
</head>
<body>
<input type="hidden" id="userId" value='<%= userId %>'>
  <input type="hidden" id="cartId" value='<%= cartId %>'>
	 	<input id="orderHistory" type="hidden" value=<%=orderDetailHistory %> />
	<header class="header" id="header">
	    </header>
	    <div class="sp" id="sp">
	    <div id="container">
      		<h1>注文履歴</h1>
	    	
	    </div>

		<script src="./js/header.js" type="module"></script>
    <script src="./js/topScrollButton.js" type="module"></script>
		<script src="./js/viewOrderHistory.js" type="module"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
</body>
</html>