<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/index.css">
</head>
<body>
<h1>ログイン</h1>

<form action="/hotmot/Login" method="post">
  <input type="text" name="name" placeholder="ユーザー名"><br>
  <input type="password" name="pass" placeholder="パスワード"><br>
  <button type="submit">ログイン</button>
</form>

</body>
</html>