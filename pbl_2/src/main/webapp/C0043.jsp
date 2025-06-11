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
.text-danger {
	color: red;
	font-size: 0.9em;
}

.content {
	max-width: 600px;
	margin: 50px auto;
	padding: 30px;
	background: #fdfdfd;
	border-radius: 8px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.btn-group {
	text-align: center;
}
</style>
</head>
<body>

	<header>
		<nav class="navbar">
			<div class="logo">物品売上管理システム</div>
			<ul class="nav-links">
				<li><a class="active" href="C0020.jsp">ダッシュボード</a></li>
				<li><a href="S0010.jsp">売上登録</a></li>
				<li><a href="S0020.jsp">売上検索</a></li>
				<li><a href="S0030.jsp">アカウント登録</a></li>
				<li><a href="C0040.jsp">アカウント検索</a></li>
				<li><a href="logout.jsp" class="logout right">ログアウト</a></li>
			</ul>
		</nav>
	</header>
	<div class="content">
		<h2>アカウント詳細編集確認</h2>

		<form action="C0043Servlet" method="post">
			<input type="hidden" name="accountId" value="${accountId}" /> <input
				type="hidden" name="name" value="${name}" /> <input type="hidden"
				name="email" value="${email}" /> <input type="hidden"
				name="password" value="${password}" />
			<c:forEach var="auth" items="${authorities}">
				<input type="hidden" name="authorities" value="${auth}" />
			</c:forEach>

			<div class="mb-3">
				<label class="form-label">氏名</label> <input type="text"
					class="form-control-plaintext" value="${name}" readonly />
			</div>

			<div class="mb-3">
				<label class="form-label">メールアドレス</label> <input type="text"
					class="form-control-plaintext" value="${email}" readonly />
			</div>

			<div class="mb-3">
				<label class="form-label">パスワード</label> <input type="password"
					class="form-control-plaintext" value="${password}" readonly />
			</div>

			<div class="mb-3">
				<label class="form-label">パスワード（確認）</label> <input type="password"
					class="form-control-plaintext" value="${password}" readonly />
			</div>

			<input type="hidden" name="authorities" value="${authorityValue}">

			<div class="mb-3">
				<label class="form-label d-block">権限</label>

				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio"
						disabled
      ${authorityValue==0 ? 'checked' : ''}> <label
						class="form-check-label">権限なし</label>
				</div>

				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio"
						disabled
      ${authorityValue== 1 || authorityValue==3 ? 'checked' : ''}>
					<label class="form-check-label">売上登録</label>
				</div>

				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio"
						disabled
      ${authorityValue== 2 || authorityValue==3 ? 'checked' : ''}>
					<label class="form-check-label">アカウント登録</label>
				</div>
			</div>

			<div class="btn-group">
				<button type="submit" class="btn btn-primary">✔ OK</button>
				<button type="button" class="btn btn-secondary"
					onclick="history.back()">キャンセル</button>
			</div>
		</form>
	</div>
</body>
</html>
