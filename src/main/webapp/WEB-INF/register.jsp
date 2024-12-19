<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3 th:text="${message}"></h3>
	<h1>会員登録画面</h1>
	<form action="/register" method="post">
		<label for="username">username:</label> <input type="text"
			id="username" name="username" required /> <label for="password">password:</label>
		<input type="password" id="password" name="password" required />
		<td><label for="name">名前:</label></td>
		<td><input type="text" id="name" name="name" class="form-input"
			required></td> <input type="hidden" name="role" value="USER">
		<td><label for="sei">姓:</label></td>
		<td><select id="sei" name="sei" class="form-input" required>
				<option value="">選択してください</option>
				<option value="男">男</option>
				<option value="女">女</option>
		</select></td>
		<td><label for="address">住所:</label></td>
		<td><input type="text" id="address" name="address"
			class="form-input" required></td>
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
		</select></td> <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
		<button type="submit">登録する</button>
</body>
</html>