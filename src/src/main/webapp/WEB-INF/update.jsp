<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="searchman.entity.Shain"%>
<%@page import="searchman.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール修正画面</title>
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
	User user = (User) request.getAttribute("user");
	%>
	<h1>プロフィール修正画面</h1>

	<form action="/update" method="post" enctype="multipart/form-data">


		<table class="form-table">
			<tr>
				<td><label for="file">プロフィール写真:</label></td>
				
				<%--<td><input type="file" name="profileImage" /></td>--%>
			</tr>
			<%-- <button type="submit">アップロード</button> --%>

			<tr>
				<td><label for="username">メール:</label></td>
				<td><%=user.getUsername()%></td>
			</tr>

			<tr>
				<td><label for="role">役割:</label></td>
				<td><select id="role" name="role" class="form-input" required>
						<option value="">選択してください</option>
						<option value="ADMIN">ADMIN</option>
						<option value="USER">USER</option>
				</select></td>
				<td>現在の設定：<%=user.getRole()%></td>
			</tr>

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
				<td><input type="text" id="address" name="address"
					value="<%=shain.getAddress()%>" class="form-input" required></td>
			</tr>
			<tr>
				<td><label for="nen">入社年度:</label></td>
				<td><%=shain.getNen()%></td>
			</tr>

			<button type="submit" class="form-button">更新</button>
			<input type="hidden" name="username" value="<%=user.getUsername()%>">
			<input type="hidden" name="UserId" value="<%=shain.getUserId()%>">
			<input type="hidden" name="id" value="<%=shain.getId()%>">
			<input type="hidden" name="name" value="<%=shain.getName()%>">
			<input type="hidden" name="sei" value="<%=shain.getSei()%>">
			<input type="hidden" name="nen" value="<%=shain.getNen()%>">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			</form>

			<form action="index" method="get">
				<input type="submit" value="一覧画面へ">
			</form>
</body>
</html>