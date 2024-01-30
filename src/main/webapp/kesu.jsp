<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>こんにちは、世界！</h1>

	<%
    // PHPの処理
    echo "今日は、" . date("Y/m/d") . "です。";

    // HTMLの処理
    <p>これは、HTMLです。</p>
  %>
</body>
</html>
</body>
</html>