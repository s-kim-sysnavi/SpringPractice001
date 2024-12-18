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
</style>
</head>
<body>
	<%-- shainから社員情報を取得する --%>
	<%
	Shain shain = (Shain) request.getAttribute("shain");
	%>
	<h1>社員情報削除画面</h1>
	<form action="delete" method="post">
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
		<button type="submit" class="form-button">削除</button>
		<input type="hidden" name="userId" value="<%=shain.getUserId()%>">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>
	<form action="top" method="get">
		<input type="submit" value="一覧画面へ">
	</form>
</body>
</html>