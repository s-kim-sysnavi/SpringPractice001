<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="searchman.entity.Shain"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報削除画面</title>
<style>
.form-input {
	width: 100%;
}

.form-table td {
	padding: 5px;
}

.form-table label {
	text-align: right;
}

.form-button {
	margin-top: 10px;
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
	flex: 1;
	padding: 20px;
	background-color: #f5f5f5;
	flex-direction: column;
	align-items: center;
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
.delete-info {
	background-color: #808080;
	color: white;
	font-weight: bold;
	padding: 10px;
	border: 2px solid #ccc;
	border-radius: 10px;
	max-width: 500px;
	margin: 20px auto;
}
</style>
</head>
<body>
	<div class="container">
		<header> ヘッダー </header>
		<main>
			<%-- shainから社員情報を取得する --%>
			<%
			Shain shain = (Shain) request.getAttribute("shain");
			%>
			<h1>社員情報削除画面</h1>
			<form action="delete" method="post" class = "delete-info">
				<table class="form-table">
					<tr>
						<td><label for="userId">ID:</label></td>
						<td><%=shain.getUserId()%></td>
					</tr>
					<tr>
						<td><label for="name">名前:</label></td>
						<td><%=shain.getName()%></td>
					</tr>
					<tr>
						<td><label for="sei">姓:</label></td>
						<td><%=shain.getSei()%></td>
					</tr>

					<tr>
						<td><label for="address">住所:</label></td>
						<td><%=shain.getAddress()%></td>
					</tr>
					<tr>
						<td><label for="nen">入社年度:</label></td>
						<td><%=shain.getNen()%></td>
					</tr>
				</table>
				<button type="submit" class="button-link">削除</button>
				<input type="hidden" name="userId" value="<%=shain.getUserId()%>">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}">
			</form>

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