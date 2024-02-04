<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<% 
String message = (String)request.getAttribute("message");
String userId = (String)session.getAttribute("userId");
String cartId = (String)session.getAttribute("cartId");
String name =(String)request.getAttribute("name"); 
String email =(String)request.getAttribute("email"); 
String password =(String)request.getAttribute("password"); 
String confirmPassword =(String)request.getAttribute("confirmPassword");
System.out.println(name);
if(userId != null && cartId != null) {
	String url = "ProductListServlet?userId=" + userId;
	response.sendRedirect(url);
	return;
}
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>新規登録</title>
    <link rel="stylesheet" href="css/global.css" />
    <link rel="stylesheet" href="css/login.css" />
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  </head>
  <body>
    <input type="hidden" id="message" value=<%= message %> />
    <div class="container">
      <form
        id="form"
        action="RegisterServlet"
        method="POST"
        class="form register"
      >
        <div class="head register">
          <div class="lock-icon">
            <i class="fa-solid fa-lock" style="color: white"></i>
          </div>
          <h3>新規登録</h3>
        </div>
        <div class="input-area">
          <label for="name" class="label">名前</label>
          <input
          	autofocus
            id="name"
            name="name"
            required
            type="text"
            placeholder="田中 太郎"
             <% if(name != null){%>
            value=<%=name %>
            <%}%>
          />
        </div>
        <div class="input-area">
          <label for="email" class="label">メールアドレス</label>
          <input
            id="email"
            name="email"
            required
            type="email"
            placeholder="xxx@gmail.com"
            <% if(email != null){%>
            value=<%=email %>
            <%}%>
          />
        </div>
        <div class="input-area">
          <label for="password" class="label">パスワード</label>
          <input
            id="password"
            name="password"
            required
            type="password"
            placeholder="********"
            minlength="8"
            <% if(password != null){%>
            value=<%=password %>
            <%}%>
          />
        </div>
        <div class="input-area">
          <label for="confirmPassword" class="label">確認用パスワード</label>
          <input
            id="confirmPassword"
            name="confirmPassword"
            required
            type="password"
            placeholder="********"
            minlength="8"
            <% if(password != null){%>
            value=<%=confirmPassword %>
            <%}%>
          />
        </div>
        <div class="btn-group">
          <a href="index.jsp" class="btn login">ログイン</a>
          <div class="btn register">新規登録<input type="submit" /></div>
        </div>
      </form>
    </div>
    <script
      type="text/javascript"
      src="https://cdn.jsdelivr.net/npm/toastify-js"
    ></script>

    <script src="js/register.js" type="module"></script>
  </body>
</html>
