<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント削除確認</title>
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
		<jsp:include page="/navbar.jsp" />
	</header>

	<main class="container mt-5">
		<h2>アカウント削除確認</h2>

		<form action="S0044.html" method="post">
			<input type="hidden" name="accountId" value="${accountId}" />

			<!-- 氏名 -->
			<div class="mb-3">
				<label class="form-label">氏名</label> <input type="text"
					class="form-control" value="${name}" readonly disabled>
			</div>

			<!-- メール -->
			<div class="mb-3">
				<label class="form-label">メールアドレス</label> <input type="text"
					class="form-control" value="${email}" readonly disabled>
			</div>

			<!-- パスワード -->
			<div class="mb-3">
				<label class="form-label">パスワード</label> <input type="password"
					class="form-control" value="${password}" readonly disabled>
			</div>

			<!-- 権限 -->
			<div class="mb-3">
				<label class="form-label">権限</label><br>

				<div class="form-check form-check-inline">
					<input type="checkbox" class="form-check-input" disabled
						<c:if test="${has0}">checked</c:if> /> <label
						class="form-check-label">権限なし</label>
				</div>
				<div class="form-check form-check-inline">
					<input type="checkbox" class="form-check-input" disabled
						<c:if test="${has1}">checked</c:if> /> <label
						class="form-check-label">売上登録</label>
				</div>
				<div class="form-check form-check-inline">
					<input type="checkbox" class="form-check-input" disabled
						<c:if test="${has2}">checked</c:if> /> <label
						class="form-check-label">アカウント登録</label>
				</div>
			</div>

			<!-- ボタン -->
			<div class="d-flex justify-content-center mt-4">
				<button type="submit" class="btn btn-danger me-2">✔ 削除</button>
				<a href="S0041.html" class="btn btn-secondary">キャンセル</a>
			</div>
		</form>

		<c:if test="${not empty error}">
			<div class="alert alert-danger mt-3">${error}</div>
		</c:if>

	</main>
</body>
</html>