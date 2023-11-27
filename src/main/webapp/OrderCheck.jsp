<%@ page import="java.util.ArrayList" %>
<%@ page import="model.OrderCheckBean" %>
<%@ page import="model.ItemsBean" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文確認</title>
</head>
<body>
	<h1>注文リスト</h1>
	
	<%
        ArrayList<OrderCheckBean> orderList = (ArrayList<OrderCheckBean>) request.getSession().getAttribute("orderList");
        if (orderList != null && !orderList.isEmpty()) {
            for (OrderCheckBean order : orderList) {
    %>
    <%
    	ArrayList<ItemsBean> itemsList = (ArrayList<ItemsBean>) request.getSession().getAttribute("itemsList");
    	if (itemsList != null && !itemsList.isEmpty()) {
        	for (ItemsBean items : itemsList) {
    %>
    	<div>
            <img src="<%= order.getImg() %>" alt="Product Image">
            <p>商品名: <%= order.getProductName() %></p>
            <p>オプション: <%= order.getOption() %></p>
            <p>注文者情報: <%= items.getcartDetailId() %> - <%= items.getuserName() %> (ステータス: <select name="status">
            		<option vaule="1">作成中</option>
            		<option vaule="2">完成</option>
            		<option vaule="3">受取済み</option>
            </select>>))</p>
       </div>
    <%
            }
        } else {
    %>
            <p>注文はまだありません。</p>
    <%
        }
    %>
</body>
</html>