<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
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
    <div class="head register">
      <div class="lock-icon">
      <i class="fa-solid fa-lock" style="color: white"></i>
      </div>
      <h3>新規登録</h3>
    </div>
      <div class="input-area">
        <label for="email" class="label">メールアドレス</label>
        <input id="email" required type="email" placeholder="xxx@gmail.com" />
      </div>
      <div class="input-area">
        <label for="password" class="label">パスワード</label>
        <input id="password" required type="password" placeholder="********" minlength="8" />
      </div>
      <div class="input-area">
        <label for="confirmPassword" class="label">確認用パスワード</label>
        <input id="confirmPassword" required type="password" placeholder="********" minlength="8" />
      </div>
      <div class="btn-group">
      	<a href="login.jsp" class="btn login">ログイン</a>
      	<div class="btn register">新規登録<input type="submit" />	</div>
      </div>
    </form>
  </div>
	
	<script src="js/register.js" type="module"></script>
</body>
</html>