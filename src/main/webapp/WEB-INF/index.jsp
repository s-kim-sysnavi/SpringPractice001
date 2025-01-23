<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="searchman.entity.Shain"%>
<%@page import="searchman.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

table {
	border-collapse: collapse; /* セルの境界線を重ねて単線にする */
}

th, td {
	border: 1px solid black; /* セルの境界線のスタイルを設定 */
	padding: 10px;
}
</style>
<title>社員一覧</title>
</head>
<body>
	<header> ヘッダー </header>
	<main>
		<h1>社員一覧</h1>



		<%
		ArrayList<Shain> shainList = (ArrayList<Shain>) request.getAttribute("shainList");
		%>


		<%
		String role = (String) request.getAttribute("role");
		String username = (String) request.getAttribute("username");
		Long currentUserId = (Long) request.getAttribute("currentUserId");
		ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");

		if ("ADMIN".equals(role)) {
		%>
		<table border="1">
			<tr bgcolor="#cccccc">
				<th>ID</th>
				<th>アカウント(email)</th>
				<th>権限</th>
				<th>名前</th>
				<th>姓</th>

				<th>住所</th>
				<th>入社年度</th>
				<th>変更</th>
				<th>削除</th>

			</tr>
			<%
			for (User user : users) {
			%>
			<tr>
				<td><%=user.getId()%></td>
				<td><%=user.getUsername()%></td>
				<td><%=user.getRole()%></td>
				<%
				for (Shain shain : shainList) {
				%>
				<%
				if (user.getId() == shain.getUserId()) {
				%>

				<td><%=shain.getName()%></td>
				<td><%=shain.getSei()%></td>

				<td><%=shain.getAddress()%></td>
				<td><%=shain.getNen()%></td>

				<td><a href="update?userId=<%=shain.getUserId()%>"
					class="button-link">変更</a></td>
				<td><a href="delete?userId=<%=shain.getUserId()%>"
					class="button-link">削除</a></td>
				<!--<td><a href="copy?userId=">複製</a></td>-->
			</tr>
			<%
}
%>
			<%
}
%>
			<%
}
%>
			<%
			} else {
			%>
			<table border="1">
				<tr bgcolor="#cccccc">
					<th>ID</th>
					<th>アカウント(email)</th>
					<th>名前</th>
					<th>姓</th>

					<th>住所</th>
					<th>入社年度</th>
					<th>変更</th>

				</tr>
				<%
				for (User user : users) {
				%>
				<tr>
					<td><%=user.getId()%></td>
					<td><%=user.getUsername()%></td>
					<%
					for (Shain shain : shainList) {
					%>
					<%
					if (user.getId() == shain.getUserId()) {
					%>


					<td><%=shain.getName()%></td>
					<td><%=shain.getSei()%></td>

					<%
					if (currentUserId == shain.getUserId()) {
					%>
					<td><%=shain.getAddress()%></td>
					<td><%=shain.getNen()%></td>

					<td><a href="update?userId=<%=shain.getUserId()%>">変更</a></td>

				</tr>
				<%
				} else {
				%>
				<td>ー</td>
				<td>ー</td>
				<td>ー</td>


				</tr>
				<%
}
%>
				<%
}
%>
				<%
				}
				;
				%>
				<%
}
%>

				<%
				}
				;
				%>

			</table>


			<p>
				<!-- 「トップ画面へ」ボタン -->
			<form action="top" method="get">
				<input type="submit" value="トップ画面へ" class="button-link">
			</form>
			</p>

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
</body>
</html>