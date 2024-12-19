<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="searchman.entity.Shain"%>
<%@page import="searchman.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員一覧</title>
<style>
table {
	border-collapse: collapse; /* セルの境界線を重ねて単線にする */
}

th, td {
	border: 1px solid black; /* セルの境界線のスタイルを設定 */
	padding: 10px;
}
</style>
</head>
<body>
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

			<td><a href="update?userId=<%=shain.getUserId()%>">変更</a></td>
			<td><a href="delete?userId=<%=shain.getUserId()%>">削除</a></td>
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
			<input type="submit" value="トップ画面へ">
		</form>
		</p>

		<div align="left">ログインユーザー情報</div>
		<div align="left">ID: ${currentUserId}</div>
		<div align=left>現在ログインしているユーザー： ${username}</div>
		<div align=left>あなたの権限： ${role}</div>
</body>
</html>