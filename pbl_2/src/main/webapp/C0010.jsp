<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css">
<style>
	body {
		background-color: #eeeeee;
	}
	.container-wrapper {
		display: flex;
		align-items: center;
		justify-content: center;
	}
	h2 {
		margin-top: 100px;
		margin-bottom: 30px;
        font-size: 32px;
	}
	.input-group {
	margin-bottom: 20px;
	}
	input[type="text"], input[type="password"] {
		width: 100%;
		padding: 12px;
		border: 1px solid #ccc;
		border-radius: 6px;
		font-size: 14px;
		box-sizing: border-box;
	}
	.input-note {
		font-size: 12px;
		color: #888;
		margin-top: 4px;
	}
	.error-message {
		color: red;
		font-size: 13px;
		margin-bottom: 15px;
	}
	.alert ul {
		margin: 0;
		padding-left: 1.2rem;
	}
	.alert li {
		margin-bottom: 4px;
	}
	button {
		background-color: #337ab7;
		color: white;
		padding: 12px;
		border: none;
		border-radius: 6px;
		cursor: pointer;
		width: 100%;
		font-size: 14px;
	}
	button:hover {
		background-color: #286090;
	}
</style>
</head>
<body>
	<div class="container-wrapper">
	<div class="login-container">
	<h2>物品売上管理システム</h2>
	<c:if test="${not empty errors}">
	<div class="alert alert-danger">
		<ul>
			<c:forEach var="err" items="${errors}">
				<li>${err}</li>
			</c:forEach>
		</ul>
	</div>
	</c:if>
	
	<form action="C0010.html" method="post">
	<div class="input-group">
	<input type="text" name="mail" placeholder="メールアドレス" value="${fn:escapeXml(mail)}">
	<div class="input-note">※必須 / 例：user@example.com</div>
	</div>
	<div class="input-group">
	<input type="password" name="password" placeholder="パスワード">
	<div class="input-note">※必須 / 半角英数字で入力</div>
	</div>
	<button type="submit">ログイン</button>
	</form>
	</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>