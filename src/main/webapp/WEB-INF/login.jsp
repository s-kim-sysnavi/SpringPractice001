<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ログイン</title>
</head>
<body>
	<h1>ログイン</h1>
	<form action="/login" method="post">
		<label for="username">Email:</label> <input type="text"
			name="username" id="username" required><br> <label
			for="password">PassWord:</label> <input type="password"
			name="password" id="password" required><br> <input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<!-- CSRF トークン -->
		<button type="submit">ログイン</button>
	</form>
	</form>
	<form action="/register" method="get">
		<button type="submit">会員登録</button>
	</form>
</body>
</html>
