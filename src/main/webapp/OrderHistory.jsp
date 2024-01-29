<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.OrderDetailBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
<link rel="stylesheet" type="text/css" href="css/OrderHistory.css">
</head>
<body>
<% ArrayList<OrderDetailBean> orderDetailHistory=(ArrayList<OrderDetailBean>)request.getAttribute("orderDetailHistory"); %> 
	<h1>注文履歴</h1>
	<!-- アコーディオンメニュー -->
	<%  
	int id, orderId, productId, riceId, quantity, status;
	String createdAt;
 	%>
	<% for(OrderDetailBean history: orderDetailHistory){ %>
	    id = history.getId();
 		orderId = history.getOrderId();
 		productId = history.getProductId();
 		riceId = history.getRiceId();
 		quantity = history.getQuantity(); 
 		status = history.getStatus();
 		createdAt = new SimpleDateFormat("yyyy/MM/dd").format(history.getCreatedAt());

 		<details class="details">
 	   	<summary class="details-summary">createdAt</summary>
		 <p class="details-content">
		    orderId。<br>
		    productId<br>
		    riceId<br>
		    quantity<br>
		    status<br>
		  </p>
		</details>
		</p>
		</details>
	<% } %>
</body>
</html>