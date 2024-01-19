<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = (String)session.getAttribute("message");
	System.out.println("message: " + message);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ブックマーク</title>
<link rel="stylesheet" href="css/bookMark.css" />
<link rel="stylesheet" href="css/global.css" />
<link rel="stylesheet" href="css/header.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
<link
  href="https://use.fontawesome.com/releases/v6.2.0/css/all.css"
  rel="stylesheet"
/>
<link rel='icon' href='images/favicon.ico' />
</head>
<body>
<input type="hidden" id="message" value="<%= message %>">
<header class="header" id="header">
    </header>
    <div class="sp" id="sp">
	<h1>ブックマーク</h1>
	
	<script src="js/viewBookMark.js" type="module"></script>
	<script src="./js/header.js" type="module"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
</body>
</html>