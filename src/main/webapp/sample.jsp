<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert date</title>
</head>
<body>
    <form action="SampleServlet" method="post">
        Name: <input type="text" name="name" /><br>
        <input type="hidden" name="world" value="Hello" />
        <input type="submit" value="Insert Data">
    </form>
</body>
</html>