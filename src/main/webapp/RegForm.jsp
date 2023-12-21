<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<form action="/servlet/RegUserContServ" method="post">
eメール   :<input type="text" id="email" name="email" required><br>
名前      :<input type="text" id="name" name="name" required><br>
パスワード:<input type="password" id="pass" name="pass" required><br>
<input type="submit" value="確認">
</form>
</body>
</html>