<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%
	String userId = (String)session.getAttribute("userId");
	if(userId == null) {
		response.sendRedirect("index.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>管理者画面</title>
    <link rel="stylesheet" href="css/adminScreen.css" />
    <link rel="stylesheet" href="css/global.css" />
    <link rel="stylesheet" href="css/header.css" />
    <link rel="stylesheet" href="css/topScrollButton.css" />
    <link
      href="https://use.fontawesome.com/releases/v6.2.0/css/all.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css"
    />
    <link rel="icon" href="images/favicon.ico" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  </head>
  <body>
    <input type="hidden" id="userId" value="<%= userId %>" />
    <div class="container">
    <div class="action-area">
    <input id="selectDate" class="select-date" type="date" />
   <a class="action-button logout" href="LogoutServlet" id="logout">ログアウト</a>
    </div>
    <div class="histories" id="histories">
    </div>
    </div>
    <!-- 	<script src="./js/header.js" type="module"></script> -->
    <script src="./js/topScrollButton.js" type="module"></script>
    <script src="./js/viewAdminScreen.js" type="module"></script>
    <script
      type="text/javascript"
      src="https://cdn.jsdelivr.net/npm/toastify-js"
    ></script>
  </body>
</html>
