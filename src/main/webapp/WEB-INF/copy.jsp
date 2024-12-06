<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="searchman.entity.Shain"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>複製内容確認画面</title>
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
	<h1>複製内容確認画面</h1>
	<form action="copy" method="post">
		<table class="form-table">
			<tr>
				<td><label for="name">名前:</label></td>
				<td><%=shain.getName()%></td>
			</tr>
			<tr>
				<td><label for="sei">姓:</label></td>
				<td><%=shain.getSei()%></td>
			</tr>
			<tr>
				<td><label for="nen">年:</label></td>
				<td><%=shain.getNen()%></td>
			</tr>
			<tr>
				<td><label for="address">住所:</label></td>
				<td><%=shain.getAddress()%></td>
			</tr>
		</table>
		<button type="submit" class="form-button">複製</button>
		<input type="hidden" name="name" value="<%=shain.getName()%>">
		<input type="hidden" name="sei" value="<%=shain.getSei()%>"> <input
			type="hidden" name="nen" value="<%=shain.getNen()%>"><input
			type="hidden" name="address" value="<%=shain.getAddress()%>">
	</form>
</body>
</html>