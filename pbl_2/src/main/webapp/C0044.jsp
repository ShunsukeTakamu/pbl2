<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>物品売上管理システム</title>
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

	<div class="container mt-5">
		<h2>アカウント詳細削除確認</h2>

		<form action="C0044.html" method="post">

			<input type="hidden" name="accountId" value="${accountId}" /> <input
				type="hidden" name="name" value="${fn:escapeXml(name)}" /> <input
				type="hidden" name="email" value="${fn:escapeXml(email)}" /> <input
				type="hidden" name="password" value="${fn:escapeXml(password)}" />
			<c:forEach var="auth" items="${authorities}">
				<input type="hidden" name="authorities" value="${auth}" />
			</c:forEach>

			<div class="mb-3">
				<label class="form-label">氏名</label> <input type="text"
					class="form-control" value="${fn:escapeXml(name)}" readonly
					disabled />
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
					<input type="checkbox" class="form-check-input" value="0"disabled ${has0 ? 'checked' : ''}>
					<label class="form-check-label">権限なし</label>
				</div>

				<div class="form-check form-check-inline">
					<input type="checkbox" class="form-check-input" value="1"disabled ${has1 ? 'checked' : ''}>
					<label class="form-check-label">売上登録</label>
				</div>

				<div class="form-check form-check-inline">
					<input type="checkbox" class="form-check-input" value="2"disabled ${has2 ? 'checked' : ''}>
					<label class="form-check-label">アカウント登録</label>
				</div>
			</div>

			<div class="d-flex justify-content-center mt-4">
				<button type="submit" class="btn btn-primary me-2">✔ OK</button>
				<button type="button" class="btn btn-secondary"
					onclick="history.back()">キャンセル</button>
			</div>
		</form>
	</div>
</body>
</html>
