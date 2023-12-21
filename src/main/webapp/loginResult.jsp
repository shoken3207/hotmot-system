<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%

User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(loginUser != null) {  %>
  <p>ログインに成功しました</p>
  <p>ようこそ<%= loginUser.getName() %>さん</p>
  <a href="">メニュー画面へ</a>
<% } else { %>
  <p>ログインに失敗しました</p>
  <a href="/hotmot/index.jsp">TOPへ</a>
<% } %>
</body>
</html>