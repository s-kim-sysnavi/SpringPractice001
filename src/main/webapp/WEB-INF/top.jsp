<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="searchman.entity.Shain"%>
<%@page import="searchman.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ画面</title>
<style>
html, body {
	margin: 0;
	padding: 0;
	height: 100%;
	box-sizing: border-box;
}

.container {
	display: flex;
	flex-direction: column;
	height: 100%;
}

header {
	height: 50px;
	background-color: rgb(0, 200, 255);
	color: white;
	text-align: center;
	line-height: 50px;
}

main {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	flex: 1;
	padding: 20px;
	background-color: #f5f5f5;
	box-sizing: border-box;
}

footer {
	height: 50px;
	background-color: rgb(0, 200, 255);
	color: white;
	text-align: center;
	line-height: 50px;
}

.circle-image {
	width: 120px;
	height: 120px;
	border-radius: 50%;
	object-fit: cover;
	transition: box-shadow 0.3s ease;
}

.circle-image:hover {
	box-shadow: 0 4px 8px rgba(0, 150, 255, 0.9);
}

.button-link {
	display: inline-block;
	padding: 10px 20px;
	margin:5px;
	color: white;
	background-color: #007BFF;
	text-decoration: none;
	border-radius: 5px;
	font-size: 11px;
}

.button-link:hover {
	background-color: #0056b3;
}

.login-info {
	background-color: #808080;
	color: white;
	font-weight: bold;
	padding: 10px;
	border: 2px solid #ccc;
	border-radius: 10px;
	max-width: 350px;
	margin: 20px auto;
}

#clock {
	position: absolute;
	top: 55px;
	right: 10px;
	font-size: 1.5rem;
	color: #333;
}
</style>
</head>
<body>
	<script src="/js/clock.js"></script>
	<div class="container">
		<header> ヘッダー </header>
		<main>


			<form action="/logout" method="post">
				<button class="button-link" type="submit">ログアウト</button>
			</form>
			<a id="clock"> </a>
			<%
			Long currentUserId = (Long) request.getAttribute("currentUserId");
			Shain shain = (Shain) request.getAttribute("shain");
			%>
			<p>TSET用：8</p>
			<p>
				<label for="file">プロフィール写真:</label>
			</p>
			<p>
				<%
				if (shain != null) {
				%>
				<a href="/profile?userId=<%=shain.getUserId()%>"> <img
					src="/profile/<%=shain.getProfileImage()%>" alt="プロフィール画像"
					class="circle-image">
				</a>
				<%
				} else {
				%>
			
			<p>ユーザー情報が見つかりません。</p>
			<%
			}
			%>

			</p>


			<a href="update?userId=<%=currentUserId%>" class="button-link">プロフィール修正</a>
			</li> <a href="index" class="button-link">社員情報一覧</a>
			<!--  
		String role = (String) request.getAttribute("
			role");
		if("ADMIN".equals(role)) {
		
		<li><a href="index">社員情報一覧</a></li>
		
		};
		-->




			<div align="left" class="login-info">
				<p>ログインユーザー情報</p>
				<p>ID:</p>
				<p align="right">${currentUserId}</p>
				<p>現在ログインしているユーザー：</p>
				<p align="right">${username}</p>
				<p>あなたの権限：</p>
				<p align="right">${role}</p>
			</div>
		</main>
		<footer> フッター</footer>
	</div>
</body>
</html>