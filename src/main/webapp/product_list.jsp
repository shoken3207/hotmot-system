<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product_list</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Product_list.css">

</head>
<body>
	<header class="header">
	  <div class="navtext-container">
	  	<div class="navtext">弁当</div>
	  </div>
	  <input type="checkbox" class="menu-btn" id="menu-btn">
	  <label for="menu-btn" class="menu-icon"><span class="navicon"></span></label>
	   <ul class="menu">
	      <li class="top"><a href="#home">ホーム</a></li>
	      <li><a href="#skills">ブックマーク</a></li>
	      <li><a href="#projects">カート</a></li>
	      <li><a href="#contact">注文履歴</a></li>
	      
	    </ul>
	
	  </header>
	  
	  <%
	// 値を取得
	  String text = (String) request.getAttribute("text");

	  // 値を表示
	  System.out.println(text);
		// サーブレットからJSONデータを取得
		//Map<String, Object> json = request.getAttribute("json");
	
		//String productId = request.getParameter("productId");
		//if (productId != null) {
		    //json = "{\"productId\":" + productId + "}";
		    %>
		
<%-- 		<%-- JSONデータを表示 --%>
<!-- 		<pre> -->
<%-- 			${json} --%>
<!-- 		</pre> -->
	


</body>
</html>