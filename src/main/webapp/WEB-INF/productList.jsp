<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.BookMarkBean" %>
<%@ page import="java.util.ArrayList" %>
<%
	String userId = (String)session.getAttribute("userId");
	String cartId = (String)session.getAttribute("cartId");
	if(userId == null || cartId == null) {
		response.sendRedirect("index.jsp");
		return;
	}
	String bookMarks = (String)session.getAttribute("bookMarks");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品一覧</title>
    <link rel="stylesheet" href="css/test.css" />
    <link rel="stylesheet" href="css/global.css" />
    <link rel="stylesheet" href="css/header.css" />
    <link rel="stylesheet" href="css/tabs.css" />
    <link rel="stylesheet" href="css/topScrollButton.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
    <link
      href="https://use.fontawesome.com/releases/v6.2.0/css/all.css"
      rel="stylesheet"
    />
    <link rel='icon' href='images/favicon.ico' />
    <script src="js/master.js" type="module"></script>
    <script src="js/viewProductLists.js" type="module"></script>
    <script src="./js/header.js" type="module"></script>
    <script src="./js/topScrollButton.js" type="module"></script>
  </head>
  <body>
  <input type="hidden" id="userId" value='<%= userId %>'>
  <input type="hidden" id="cartId" value='<%= cartId %>'>
<input type="hidden" id="bookMarks" value='<%= bookMarks %>'>
   <header class="header" id="header">
    </header>
    <div class="sp" id="sp">

    <div class="container">
      <div class="tabs" id="tabs"></div>
      <div class="lists" id="lists"></div>
    </div>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
  </body>
</html>
