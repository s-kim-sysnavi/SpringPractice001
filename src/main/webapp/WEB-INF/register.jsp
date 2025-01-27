<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"

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

form {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.button-link {
	display: inline-block;
	padding: 10px 20px;
	margin: 5px;
	color: white;
	background-color: #007BFF;
	text-decoration: none;
	border-radius: 5px;
	font-size: 11px;
	border: none;
	color: white
}

.button-link:hover {
	background-color: #0056b3;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="container">

		<header> ヘッダー </header>
		<main>
			<h3 th:text="${message}"></h3>
			<h1>会員登録画面</h1>
			<form action="/register_success" method="post">

				<label for="username">username:</label> <input type="text"
					id="username" name="username" required /> <label for="password">password:</label>
				<input type="password" id="password" name="password" required /> <label
					for="name">名前:</label> <input type="text" id="name" name="name"
					class="form-input" required> <input type="hidden"
					name="role" value="USER"> <label for="sei">姓:</label> <select
					id="sei" name="sei" class="form-input" required>
					<option value="">選択してください</option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select> <label for="address">住所:</label> <input type="text" id="address"
					name="address" class="form-input" required> <label
					for="nen">入社年度:</label> <select id="nen" name="nen"
					class="form-input" required>
					<option value="">選択してください</option>
					<%
					for (int year = 2001; year <= 2024; year++) {
					%>
					<option value="<%=year%>"><%=year%></option>
					<%
					}
					%>

				</select> <input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}"> <input type="hidden"
					name="profileImage" value="default.png">
				<button class="button-link" type="submit">登録する</button>
			</form>
			<a href="login" class="button-link">ログイン画面に戻る</a>
		</main>
		<footer> フッター</footer>
	</div>
</body>
</html>