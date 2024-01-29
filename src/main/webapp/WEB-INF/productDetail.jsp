<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="models.BookMarkBean" %>
<%@ page import="java.util.ArrayList" %>
<%
	String bookMarks = (String)session.getAttribute("bookMarks");
	String productId = (String)session.getAttribute("productId");
	System.out.println("productId: " + productId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>商品詳細</title>
<link rel="stylesheet" href="css/productDetail.css" />
<link rel="stylesheet" href="css/global.css" />
<link rel="stylesheet" href="css/header.css" />
<link
  href="https://use.fontawesome.com/releases/v6.2.0/css/all.css"
  rel="stylesheet"
/>
<link rel='icon' href='images/favicon.ico' />
<script src="js/viewProductDetail.js" type="module"></script>
<script src="./js/header.js" type="module"></script>
</head>
<body>
<input type="hidden" id="bookMarks" value='<%= bookMarks %>'>
	<header class="header" id="header">
    </header>
    <div class="sp" id="sp">
<input type="hidden" id="productId" value="<%= productId %>">
<div class="container" id="container">
	<a class="back-btn" id="backBtn" href="/hotmot/ProductListServlet"></a>
</div>
</body>
</html>