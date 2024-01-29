<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
	
	int userId = (int) session.getAttribute("userId");
	System.out.println("userId: " + userId);
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/loginResult.css">
<title>Insert title here</title>
</head>
<body>
  <p>ログインに成功しました</p>
  <a href="http://localhost:8080/hotmot/ProductListServlet">メニュー画面へ</a>
</body>
</html>