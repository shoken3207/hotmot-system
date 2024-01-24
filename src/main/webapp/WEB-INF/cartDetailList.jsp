<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.CartDetailBean, java.util.Arrays" %>
<%
// 	ArrayList<CartDetailBean> cartDetails = (ArrayList<CartDetailBean>) session.getAttribute("cartDetails");
// 	int num = (int) session.getAttribute("num");
// 	System.out.println("num: " + num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/cartDetailList.css" />
    <link rel="stylesheet" href="css/global.css" />
    <link rel="stylesheet" href="css/header.css" />
    <link
      href="https://use.fontawesome.com/releases/v6.2.0/css/all.css"
      rel="stylesheet"
    />
    <link rel='icon' href='images/favicon.ico' />
    <script src="js/master.js" type="module"></script>
    <script src="js/viewCartDetailList.js" type="module"></script>
    <script src="./js/header.js" type="module"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>カート</title>
</head>
<body>
	<header class="header" id="header">
    </header>
    <div class="sp" id="sp">
    <div class="action-group">
    	<button id="updateCart">カート情報を更新</button>
    	<button id="order">注文を確定</button>
    </div>
    <div class="cart-detail-list" id="cartDetailList" ></div>
</body>
</html>