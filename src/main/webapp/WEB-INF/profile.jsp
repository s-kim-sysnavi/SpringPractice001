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
	border: none;
}

.button-link:hover {
	background-color: #0056b3;
}

.drag-and-drop {
	width: 60%;
	height: 100px;
	border: 2px dashed #007BFF;
	border-radius: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: #f9f9f9;
	color: #999;
}

.drag-and-drop.dragover {
	background-color: #e0e0e0;
	border-color: #0056b3;
}

.circle-image {
	width: 120px;
	height: 120px;
	border-radius: 50%;
	object-fit: cover;
	transition: box-shadow 0.3s ease;
}

.preview {
	display: flex;
	flex-wrap: wrap;
	margin-top: 10px;
	gap: 10px;
	justify-content: center;
	align-items: center;
}

.preview img {
	width: 120px;
	height: 120px;
	border-radius: 50%;
	object-fit: cover;
	border: 2px solid #ccc;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
	border-color: #007BFF;
}

.dragged {
	background-color: #555;
	color: white;
}
</style>
<title>プロフィール画像</title>
</head>
<body>
	<script src="/js/profile_upload.js"></script>
	<div class="container">
		<header> ヘッダー </header>
		<main>
			<p><%=shain.getUserId()%></p>
			<p>
				<label for="file">▼現在のプロフィール画像:</label>
			</p>
			<p>
				<img src="/profile/<%=shain.getProfileImage()%>" alt="プロフィール画像"
					class="circle-image">
			</p>

			<p>▼今回、アップロードする画像</p>
			<p>
			<div id="drag-and-drop" class="drag-and-drop">
				<p>ここにファイルをドラッグアンドドロップしてください。</p>
			</div>
			</p>

			<p id="file-info"></p>
			<div id="preview" class="preview"></div>

			<form id="upload-form" action="/profile" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="userId" value="<%=shain.getUserId()%>">
				<input type="file" name="profileImage" id="file"
					style="display: none;"> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}">
				<p>
					<button type="submit" class="button-link">アップロード</button>
				</p>
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
	</div>
	>
</body>
</html>