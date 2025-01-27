<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
html, body {
	margin: 0;
	padding: 0;
	height: 100%;
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
	flex: 1;
	padding: 20px;
	background-color: #f5f5f5;
}

footer {
	height: 50px;
	background-color: rgb(0, 200, 255);
	color: white;
	text-align: center;
	line-height: 50px;
}

.button-link {
	display: inline-block;
	padding: 10px 20px;
	color: white;
	background-color: #007BFF;
	text-decoration: none;
	border-radius: 5px;
	font-size: 11px;
	border: none;
	margin:10px;
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
</style>
<title>エラーページ</title>
</head>
<body>
	<div class="container">
		<header> ヘッダー </header>
		<main>
			<p>エラーページ</p>
			<form action="index" method="get">
				<input type="submit" value="一覧画面へ" class="button-link">
			</form>
			<form action="top" method="get">
				<input type="submit" value="トップ画面へ" class="button-link">
			</form>
		</main>
		<footer> フッター</footer>
	</div>
</body>
</html>