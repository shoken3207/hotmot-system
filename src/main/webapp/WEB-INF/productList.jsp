<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name=”viewport” content=”width=device-width,initial-scale=1.0″>
    <title>商品一覧</title>
    <link rel="stylesheet" href="css/test.css" />
    <link rel="stylesheet" href="css/global.css" />
    <link rel="stylesheet" href="css/header.css" />
    <link
      href="https://use.fontawesome.com/releases/v6.2.0/css/all.css"
      rel="stylesheet"
    />
    <link rel='icon' href='images/favicon.ico' />
    <script src="js/test.js" type="module"></script>
    <script src="js/master.js" type="module"></script>
    <script src="js/viewProductLists.js" type="module"></script>
    <script src="js/drawer.js" type="module"></script>
  </head>
  <body>
    <header class="header">
      <div class="header-container">
        <a class="logo" href="#">
          <h1><img src="./images/logo.png" /></h1>
        </a>
        <nav class="nav-items">
          <ul class="menu">
            <li class="top">
              <a href="#"
                ><i class="fa-solid fa-house"></i><span>ホーム</span></a
              >
            </li>
            <li>
              <a href="#"
                ><i class="fa-solid fa-star"></i><span>ブックマーク</span></a
              >
            </li>
            <li>
              <a href="/hotmot/CartDetailListServlet"
                ><i class="fa-solid fa-cart-shopping"></i><span>カート</span></a
              >
            </li>
            <li>
              <a href="#"
                ><i class="fa-solid fa-clock-rotate-left"></i
                ><span>注文履歴</span></a
              >
            </li>
          </ul>
        </nav>
        <div class="hamburger" id="js-hamburger">
          <span class="hamburger__line hamburger__line--1"></span>
          <span class="hamburger__line hamburger__line--2"></span>
          <span class="hamburger__line hamburger__line--3"></span>
        </div>
      </div>
    </header>
    <div class="sp" id="sp">
      <nav class="nav">
        <ul class="menu">
          <a href="#"
            ><li><i class="fa-solid fa-house fa-lg"></i><span>ホーム</span></li></a
          >
          <a href="#"
            ><li>
              <i class="fa-solid fa-star fa-lg"></i><span>ブックマーク</span>
            </li></a
          >
          <a href="/hotmot/CartDetailListServlet"
            ><li>
              <i class="fa-solid fa-cart-shopping fa-lg"></i><span>カート</span>
            </li></a
          >
          <a href="#"
            ><li>
              <i class="fa-solid fa-clock-rotate-left fa-lg"></i><span>注文履歴</span>
            </li></a
          >
        </ul>
      </nav>
      <div class="black-bg" id="js-black-bg"></div>
    </div>

    <div class="container">
      <div class="tabs" id="tabs"></div>
      <div class="lists" id="lists"></div>
    </div>
  </body>
</html>
