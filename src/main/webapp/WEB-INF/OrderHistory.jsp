<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.OrderDetailBean" %>
<%@ page import="java.util.Date" %>
<%
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
	<link href="https://use.fontawesome.com/releases/v6.2.0/css/all.css" rel="stylesheet"/>
	<link rel='icon' href='images/favicon.ico' />
</head>
<body>
	 	<input id="orderHistory" type="hidden" value=<%=orderDetailHistory %> />
	<header class="header" id="header">
	    </header>
	    <div class="sp" id="sp">
	    <div id="orderHistoryList">
	    </div>

		<script src="./js/header.js" type="module"></script>
		<script src="./js/viewOrderHistory.js" type="module"></script>
</body>
</html>