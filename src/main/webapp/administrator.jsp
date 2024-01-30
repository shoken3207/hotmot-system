<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/administrator.css">
</head>

<body>
    <div class="login-wrapper" id="login">
        <div class="container">
            <div class="login">
                <div class="login-wrapper-title">
                    <h1>管理者ログイン</h1>
                </div>
                <form class="login-form" action="/hotmot/Login" method="post">
                    <div class="form-group">
                        <p>メールアドレス</p>
                        <input type="email" name="email" placeholder="メールアドレス" required>
                    </div>
                    <div class="form-group">
                        <p>パスワード</p>
                        <input type="password" name="pass" placeholder="パスワード" required>
                    </div>
                    <button type="submit" class="btn btn-submit">ログイン</button>
                </form>
            </div>
        </div>
    </div>
</body>

</html>