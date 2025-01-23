<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="searchman.entity.Shain"%>
<%@page import="searchman.entity.User"%>
<%
Shain shain = (Shain) request.getAttribute("shain");
User user = (User) request.getAttribute("user");
%>
<head>
<style>
body {
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background-color: #f5f5f5;
}

header, footer {
	width: 100%;
	height: 50px;
	background-color: rgb(0, 200, 255);
	color: white;
	text-align: center;
	line-height: 50px;
}

main {
	text-align: center;
	background-color: white;
	padding: 150px;
	border: 1px solid #ccc;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
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
</style>
<title>プロフィール画像</title>
</head>
<body>
	<header> ヘッダー </header>
	<main>
		<p><%=shain.getUserId()%></p>
		<p>
			<label for="file">現在のプロフィール写真:</label>
		</p>
		<p>
			<img src="/profile/<%=shain.getProfileImage()%>" alt="プロフィール画像"
				class="circle-image">
		</p>

		<p>
		<form action="/profile" method="post" enctype="multipart/form-data" >
			<p>
				<label for="file">新しいプロフィル画像を追加:</label>
			</p>
			<p>
				<input type="file" name="profileImage" id="file">
			</p>

			<p>
				<button type="submit" class="button-link">アップロード</button>
			</p>
			<input type="hidden" name="userId" value="<%=shain.getUserId()%>">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
		</form>
		</p>

		<p>
			<a href="update?userId=<%=shain.getUserId()%>" class="button-link">プロフィール修正画面へ戻る</a>
		</p>
		<!-- 「トップ画面へ」ボタン -->
		<p>
		<form action="top" method="get">
			<input type="submit" value="トップ画面へ" class="button-link">
		</form>
		</p>
	</main>
	<footer> フッター</footer>
</body>
</html>