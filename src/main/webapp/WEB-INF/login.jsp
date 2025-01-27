<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

.button-link {
	display: inline-block;
	padding: 10px 20px;
	margin: 10px; color : white;
	background-color: #007BFF;
	text-decoration: none;
	border-radius: 5px;
	font-size: 11px;
	border: none;
	color: white
}

form {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.button-link:hover {
	background-color: #0056b3;
}

#clock {
	position: absolute;
	top: 35px;
	right: 10px;
	font-size: 1.5rem;
	color: #333;
}
</style>
<title>ログイン</title>
</head>
<body>

	<script src="/js/clock.js"></script>

	<div class="container">
		<header> ヘッダー </header>
		<main>
			<h1>ログイン</h1>

			<p>${message}</p>
			<form action="/login" method="post">
				<label for="username">Email:</label> <input type="text"
					name="username" id="username" required><br> <label
					for="password">PassWord:</label> <input type="password"
					name="password" id="password" required><br> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<!-- CSRF トークン -->
				<button class="button-link" type="submit">ログイン</button>
			</form>
			<form action="/register" method="get">
				<button class="button-link" type="submit">会員登録</button>
			</form>
			<p id="clock"></p>
		</main>

		<footer> フッター</footer>
	</div>
</body>

</html>
