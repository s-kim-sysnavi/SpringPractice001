<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="searchman.entity.Shain"%>
<%@page import="searchman.entity.User"%>
<%
Shain shain = (Shain) request.getAttribute("shain");
User user = (User) request.getAttribute("user");
%>
<head>

<title>プロフィール画像</title>
</head>
<body>
	<p><%=shain.getUserId()%></p>
	<p>
		<label for="file">現在のプロフィール写真:</label>
	</p>
	<p>
		<img src="/profile/<%=shain.getProfileImage()%>" alt="プロフィール画像"
			class="circle-image">
	</p>

	<p>
	<form action="/profile" method="post" enctype="multipart/form-data">
		<p>
			<label for="file">新しいプロフィル画像を追加:</label>
		</p>
		<p>
			<input type="file" name="profileImage" id="file">
		</p>

		<p>
			<button type="submit">アップロード</button>
		</p>
		<input type="hidden" name="userId" value="<%=shain.getUserId()%>">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>
	</p>

	<p>
		<a href="update?userId=<%=shain.getUserId()%>">アップデート画面へ戻る</a>
	</p>
	<!-- 「トップ画面へ」ボタン -->
	<p>
	<form action="top" method="get">
		<input type="submit" value="トップ画面へ">
	</form>
	</p>
</body>
</html>