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
<title>Insert title here</title>
</head>
<body>
  <p>ログインに成功しました</p>
  <a href="https://school-hotmot.vercel.app/Home">メニュー画面へ</a>
</body>
</html>