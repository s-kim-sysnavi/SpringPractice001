<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="searchman.entity.User"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>社員情報登録画面</title>
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
</style>
</head>

<body>
	<h1>社員情報登録画面</h1>

	<form action="insert" method="post">
		<table class="form-table">
			<tr>
				<td><label for="id">ID:</label></td>
				<%
				String role = (String) request.getAttribute("role");

				if ("ADMIN".equals(role)) {
				%>
				<td>
					<%
					ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
					%> <select id="userDropdown" name="userIdEmail">
						<%
						for (User user : users) {
						%>
						<option value="<%=user.getId()%>:<%=user.getUsername()%>">
							<%=user.getId()%>:<%=user.getUsername()%>:<%=user.getRole()%>
						</option>
						<%
						}
						%>
				</select>
				</td>
				<%
				} else {
				%>
				<td>${currentUserId}<input type="hidden" name="userId"
					value="${currentUserId}"></td>
				<%
				}
				;
				%>

			</tr>
			<tr>
				<td><label for="name">名前:</label></td>
				<td><input type="text" id="name" name="name" class="form-input"
					required></td>
			</tr>
			<tr>
				<td><label for="sei">姓:</label></td>
				<td><select id="sei" name="sei" class="form-input" required>
						<option value="">選択してください</option>
						<option value="男">男</option>
						<option value="女">女</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="email">Email:</label></td>
				<%
				if ("ADMIN".equals(role)) {
				%>
				<td>選択したアカウントのemailで自動入力</td>
				<%
				} else {
				%>
				<td>${username}<input type="hidden" name="email"
					value="${username}"></td>
				<%
				}
				%>
			</tr>
			<tr>
				<td><label for="address">住所:</label></td>
				<td><input type="text" id="address" name="address"
					class="form-input" required></td>
			</tr>
			<tr>
				<td><label for="nen">入社年度:</label></td>
				<td><select id="nen" name="nen" class="form-input" required>
						<option value="">選択してください</option>
						<%
						for (int year = 2001; year <= 2024; year++) {
						%>
						<option value="<%=year%>"><%=year%></option>
						<%
}
%>
				</select></td>
			</tr>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
		</table>
		<button type="submit" class="form-button">登録</button>
	</form>
	<form action="index" method="get">
		<input type="submit" value="一覧画面へ">
	</form>
	<form action="top" method="get">
		<input type="submit" value="トップ画面へ">
	</form>

	<div align="left">ID: ${currentUserId}</div>
	<div align=left>現在ログインしているユーザー： ${username}</div>
	<div align=left>あなたの権限： ${role}</div>

</body>

</html>
