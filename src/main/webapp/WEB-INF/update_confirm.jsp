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

table.form-table {
	width: 100%;
	max-width: 600px;
	margin: 0 auto;
	border-collapse: collapse;
}

.circle-image {
	width: 120px;
	height: 120px;
	border-radius: 50%;
	object-fit: cover;
	transition: box-shadow 0.3s ease;
}

.circle-image:hover {
	box-shadow: 0 5px 10px rgba(0, 150, 255, 0.9);
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
	border: none;
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

.update-info {
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
			User user = (User) request.getAttribute("user");
			%>
			<h1>プロフィール修正画面</h1>

			<form action="/update" method="post" enctype="multipart/form-data"
				class="update-info">


				<table class="form-table">
					<tr>
						<td><label for="file">プロフィール写真:</label></td>
						<td><a href="/profile_upload?userId=<%=shain.getUserId()%>"><img
								src="/profile/<%=shain.getProfileImage()%>" alt="プロフィール画像"
								class="circle-image"></a></td>


					</tr>


					<tr>
						<td><label for="username">メール:</label></td>
						<td><%=user.getUsername()%></td>
					</tr>


					<%
					String role = (String) request.getAttribute("role");
					String username = (String) request.getAttribute("username");
					Long currentUserId = (Long) request.getAttribute("currentUserId");

					if ("ADMIN".equals(role) && "ADMIN".equals(user.getRole())) {
					%>
					<tr>
						<td><label for="role">権限:</label></td>
						<td><select id="role" name="role" class="form-input" required>
								<option value="ADMIN">ADMIN</option>
								<option value="USER">USER</option>
						</select></td>
						<td>(現在の権限：<%=user.getRole()%>)
						</td>
					</tr>

					<%
					} else if ("ADMIN".equals(role) && "USER".equals(user.getRole())) {
					%>
					<tr>
						<td><label for="role">役割:</label></td>
						<td><select id="role" name="role" class="form-input" required>
								<option value="">選択してください</option>
								<option value="ADMIN">ADMIN</option>
								<option value="USER">USER</option>
						</select></td>
						<td>(現在の設定：<%=user.getRole()%>)
						</td>
					</tr>
					<%
					} else {
					%>
					<tr>


						<td>現在の設定：</td>
						<td><%=user.getRole()%></td>
						<input type="hidden" name="role" value="USER">
					</tr>

					<%
					}
					%>
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
				</table>
				<button type="submit" class="button-link">更新</button>
				<input type="hidden" name="username" value="<%=user.getUsername()%>">
				<input type="hidden" name="UserId" value="<%=shain.getUserId()%>">
				<input type="hidden" name="id" value="<%=shain.getId()%>"> <input
					type="hidden" name="name" value="<%=shain.getName()%>"> <input
					type="hidden" name="sei" value="<%=shain.getSei()%>"> <input
					type="hidden" name="nen" value="<%=shain.getNen()%>"> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

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
