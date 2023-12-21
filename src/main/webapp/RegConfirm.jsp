<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servlet.UserServ" %>
<% 
UserServ regUserContServ = (UserServ) session.getAttribute("regUserContServ"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>下記のユーザーを登録しますか？</p>
<p>
eメール:<%= regUserContServ.getEmail() %><br>
名前   :<%= regUserContServ.getName() %><br>
</p>
<a href="/servlet/RegUserContServ">戻る</a>
<a href="/servlet/RegUserContServ?action=done">登録</a>
</body>
</html>