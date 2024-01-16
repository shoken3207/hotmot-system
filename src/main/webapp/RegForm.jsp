<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<form action="RegUserContServ" method="post">
eメール   :<input type="text" name="email" required><br>
名前      :<input type="text" name="name" required><br>
<!--パスワード:<input type="password" id="pass" name="pass" required><br>-->
<input type="submit" value="登録">
</form>
</body>
</html>