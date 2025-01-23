<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="searchman.entity.Shain"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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
	<%-- shain--%>
	<%
	Shain shain = (Shain) request.getAttribute("shain");
	%>
	<h1></h1>
	<form action="copy" method="post">
		<table class="form-table">
			<tr>
				<td><label for="name"></label></td>
				<td><%=shain.getName()%></td>
			</tr>
			<tr>
				<td><label for="sei"></label></td>
				<td><%=shain.getSei()%></td>
			</tr>
			<tr>
				<td><label for="email">Email:</label></td>
				<td><%=shain.getEmail()%></td>
			</tr>
			<tr>
				<td><label for="address"></label></td>
				<td><%=shain.getAddress()%></td>
			</tr>
			<tr>
				<td><label for="nen"></label></td>
				<td><%=shain.getNen()%></td>
			</tr>
		</table>
		<button type="submit" class="form-button"></button>
		<input type="hidden" name="name" value="<%=shain.getName()%>">
		<input type="hidden" name="sei" value="<%=shain.getSei()%>"> <input
			type="hidden" name="nen" value="<%=shain.getNen()%>"><input
			type="hidden" name="address" value="<%=shain.getAddress()%>">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>
	<form action="index" method="get">
		<input type="submit" value=">
	</form>
</body>
</html>