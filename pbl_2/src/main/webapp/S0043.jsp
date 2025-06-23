<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント詳細編集確認 | 物品売上管理システム</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<script src="js/bootstrap.bundle.min.js"></script>
<style>
.container {
	max-width: 500px;
	margin: 50px auto;
	padding: 30px;
}
</style>
</head>
<body>
	<header>
		<!-- ナビバーのインクルード -->
		<jsp:include page="/navbar.jsp" />
	</header>

	<main class="container mt-5">
		<h1>アカウント詳細編集確認</h1>

		<!-- boolean 権限フラグを JSTL でセット -->
		<c:set var="has0" value="false" />
		<c:set var="has1" value="false" />
		<c:set var="has2" value="false" />
		<c:forEach var="auth" items="${authorities}">
			<c:if test="${auth == '0'}">
				<c:set var="has0" value="true" />
			</c:if>
			<c:if test="${auth == '1'}">
				<c:set var="has1" value="true" />
			</c:if>
			<c:if test="${auth == '2'}">
				<c:set var="has2" value="true" />
			</c:if>
		</c:forEach>

		<!-- 表示項目 -->
		<div class="mb-3">
			<label class="form-label">氏名</label> <input type="text"
				class="form-control" value="${fn:escapeXml(name)}" readonly disabled />
		</div>

		<div class="mb-3">
			<label class="form-label">メールアドレス</label> <input type="text"
				class="form-control" value="${fn:escapeXml(email)}" readonly
				disabled />
		</div>

		<div class="mb-3">
			<label class="form-label">パスワード</label> <input type="password"
				class="form-control" value="${fn:escapeXml(password)}" readonly
				disabled />
		</div>

		<div class="mb-3">
			<label class="form-label">パスワード（確認）</label> <input type="password"
				class="form-control" value="${fn:escapeXml(password)}" readonly
				disabled />
		</div>

		<div class="mb-3">
			<label class="form-label">権限</label><br />
			<div class="form-check form-check-inline">
				<input type="checkbox" class="form-check-input"disabled ${has0 ? 'checked' : ''}>
				<label class="form-check-label">権限なし</label>
			</div>
			<div class="form-check form-check-inline">
				<input type="checkbox" class="form-check-input"disabled ${has1 ? 'checked' : ''}>
				<label class="form-check-label">売上登録</label>
			</div>
			<div class="form-check form-check-inline">
				<input type="checkbox" class="form-check-input"disabled ${has2 ? 'checked' : ''}>
				<label class="form-check-label">アカウント登録</label>
			</div>
		</div>

		<!-- ボタン -->
		<div class="d-flex justify-content-center mt-4">

			<!-- OK（更新） -->
			<form action="S0043.html" method="post" class="me-2">
				<input type="hidden" name="accountId" value="${accountId}" /> <input
					type="hidden" name="name" value="${fn:escapeXml(name)}" /> <input
					type="hidden" name="email" value="${fn:escapeXml(email)}" /> <input
					type="hidden" name="password" value="${fn:escapeXml(password)}" />
				<input type="hidden" name="passwordConfirm"
					value="${fn:escapeXml(password)}" />
				<c:forEach var="auth" items="${authorities}">
					<input type="hidden" name="authorities" value="${auth}" />
				</c:forEach>
				<button type="submit" class="btn btn-primary">✔ OK</button>
			</form>

			<!-- キャンセル（戻る） -->
			<form action="S0042.html" method="post">
				<input type="hidden" name="accountId" value="${accountId}" /> <input
					type="hidden" name="name" value="${fn:escapeXml(name)}" /> <input
					type="hidden" name="email" value="${fn:escapeXml(email)}" /> <input
					type="hidden" name="password" value="${fn:escapeXml(password)}" />
				<input type="hidden" name="passwordConfirm"
					value="${fn:escapeXml(password)}" /> <input type="hidden"
					name="action" value="back" />
				<c:forEach var="auth" items="${authorities}">
					<input type="hidden" name="authorities" value="${auth}" />
				</c:forEach>
				<button type="submit" class="btn btn-outline-secondary">キャンセル</button>
			</form>

		</div>
	</main>
</body>
</html>
