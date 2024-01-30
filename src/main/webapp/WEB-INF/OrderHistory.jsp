<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.OrderDetailBean" %>
<%@ page import="java.util.Date" %>


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
	<header class="header" id="header">
	    </header>
	    <div class="sp" id="sp">
	<% ArrayList<OrderDetailBean> orderDetailHistory=(ArrayList<OrderDetailBean>)request.getAttribute("orderDetailHistory"); %> 
		<!-- アコーディオンメニュー -->
		<%  
		int id, orderId, productId, riceId, quantity, status;
		Date currentDate = (Date) request.getAttribute("currentDate");
        System.out.println(currentDate);
	 	%>

		<% for(OrderDetailBean history: orderDetailHistory){ %>
		<%
		id = history.getId();
	 	orderId = history.getOrderId();
	 	productId = history.getProductId();
	 	riceId = history.getRiceId();
	 	quantity = history.getQuantity(); 
	 	status = history.getStatus();
        System.out.println(history.getCreatedAt());
// 	 	createdAt = history.getCreatedAt();
	 	%>
	 	<input id="orderHistory" type="hidden" value=<%=orderDetailHistory %> />
	 	<details class="details">
	 	<summary class="details-summary"><%=id %></summary>
	 	<p class="details-content">
	 	<%=orderId %><br>
	 	<%=productId %><br>
		<%=riceId %><br>
		<%=quantity %><br>
		<%=status %>
		</p>
		</details>
		</p>
		</details>
		<% } %>
		<script src="./js/header.js" type="module"></script>
</body>
</html>