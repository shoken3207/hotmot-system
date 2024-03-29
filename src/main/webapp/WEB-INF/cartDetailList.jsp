<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="models.CartDetailBean, java.util.Arrays"
%> <% String userId = (String)session.getAttribute("userId"); String cartId =
(String)session.getAttribute("cartId"); if(userId == null || cartId == null) {
response.sendRedirect("index.jsp"); return; } String cartDetails =
(String)session.getAttribute("cartDetails"); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/cartDetailList.css" />
    <link rel="stylesheet" href="css/global.css" />
    <link rel="stylesheet" href="css/header.css" />
    <link rel="stylesheet" href="css/topScrollButton.css" />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css"
    />
    <link
      href="https://use.fontawesome.com/releases/v6.2.0/css/all.css"
      rel="stylesheet"
    />
    <link rel="icon" href="images/favicon.ico" />
    <script src="js/master.js" type="module"></script>
    <script src="js/viewCartDetailList.js" type="module"></script>
    <script src="./js/header.js" type="module"></script>
    <script src="./js/topScrollButton.js" type="module"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>カート</title>
  </head>
  <body>
    <input type="hidden" id="userId" value=<%= userId %> />
    <input type="hidden" id="cartId" value=<%= cartId %> />
    <input type="hidden" id="cartDetails" value=<%= cartDetails %> />
    <div class="wrapper">
	    <header class="header" id="header"></header>
	    <div class="sp" id="sp"></div>
	    <div class="action-group" id="actionGroup">
	      <button class="action-button cart" id="updateCart">カートを更新</button>
	      <button class="action-button order" id="order">注文を確定</button>
	    </div>
	    <div class="cart-detail-list" id="cartDetailList"></div>
    </div>
    <script
      type="text/javascript"
      src="https://cdn.jsdelivr.net/npm/toastify-js"
    ></script>
  </body>
</html>
