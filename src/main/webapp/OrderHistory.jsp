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
<%
//リクエストスコープからのデータの取得
request.getAttribute("orderHitory");
%>

</head>
<body>
	<h1>注文履歴</h1>
	<!-- 	<!-- アコーディオンメニュー -->
	<details class="details">
	<summary class="details-summary"> <%=request.getAttribute("orderId") %></summary>
	<p class="details-content">
	<% ArrayList<OrderDetailBean> order = (ArrayList<OrderDetailBean>)request.getAttribute("orderDetailHistory"); %>	
    <% for(OrderDetailBean zzz: order) { %>
        <%=zzz %><br>
    <% } %>
	</details>
	<p><%=request.getAttribute("orderHistory") %></p>
</body>
</html>