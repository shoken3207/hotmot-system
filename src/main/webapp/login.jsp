<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/global.css" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
<link
  href="https://use.fontawesome.com/releases/v6.2.0/css/all.css"
  rel="stylesheet"
/>
<link rel='icon' href='images/favicon.ico' />
</head>
<body>
	<div class="container">
    <form id="form" action="" class="form">
    <div class="head login">
      <div class="lock-icon">
      <i class="fa-solid fa-lock-open" style="color: white"></i>
      </div>
      <h3>ログイン</h3>
    </div>
      <div class="input-area">
        <label for="email" class="label">メールアドレス</label>
        <input id="email" required type="email" placeholder="xxx@gmail.com" />
      </div>
      <div class="input-area">
        <label for="password" class="label">パスワード</label>
        <input id="password" required type="password" placeholder="password" minlength="8" />
      </div>
      <div class="btn-group">
      	<a href="register.jsp" class="btn register">新規登録</a>
      	<div class="btn login">ログイン<input type="submit" />	</div>
      </div>
    </form>
  </div>
	
	<script src="js/login.js" type="module"></script>
</body>
</html>