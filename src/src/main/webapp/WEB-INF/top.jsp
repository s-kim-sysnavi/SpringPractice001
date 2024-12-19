<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ画面</title>
</head>
<body>

	<form action="/logout" method="post">
		<button type="submit">ログアウト</button>
	</form>
	<%
	Long currentUserId = (Long) request.getAttribute("currentUserId");
	%>
	<ul>
		<li><a href="update?userId=<%=currentUserId%>">プロフィール修正</a></li>
		<li><a href="index">社員情報一覧</a></li>
		<!--  
		String role = (String) request.getAttribute("
			role");
		if("ADMIN".equals(role)) {
		
		<li><a href="index">社員情報一覧</a></li>
		
		};
		-->
	</ul>



	<div align="left">ログインユーザー情報</div>
	<div align="left">ID: ${currentUserId}</div>
	<div align=left>現在ログインしているユーザー： ${username}</div>
	<div align=left>あなたの権限： ${role}</div>
</body>
</html>