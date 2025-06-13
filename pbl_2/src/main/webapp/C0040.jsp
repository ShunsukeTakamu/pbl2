<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<script src="js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html lang="ja">
<head>


<style>
.form-group {
	display: flex;
	align-items: center;
	margin-bottom: 1rem;
}

.form-group label {
	width: 200px;
	margin-right: 10px;
	text-align: right;
}

.form-group {
	flex: 1;
}

form {
	max-width: 500px;
	margin: 0 auto;
}

.note-group {
	align-items: flex-start;
}

.note-group label {
	margin-top: 6px;
}

.short-input {
	width: 150px;
}

.wide-input {
	width: 300px;
}
</style>

<meta charset="UTF-8">
<title>物品売上管理システム</title>

</head>
<body>
	<%
	String uri = request.getRequestURI();
	%>
	<header>
		<nav class="navbar">
			<div class="logo">物品売上管理システム</div>
			<ul class="nav-links">
				<li><a class="<%=uri.endsWith("C0020.jsp") ? "active" : ""%>"
					href="C0020.html">ダッシュボード</a></li>
				<li><a class="<%=uri.endsWith("S0010.jsp") ? "active" : ""%>"
					href="S0010.html">売上登録</a></li>
				<li><a class="<%=uri.endsWith("S0020.jsp") ? "active" : ""%>"
					href="S0020.html">売上検索</a></li>
				<li><a class="<%=uri.endsWith("S0030.jsp") ? "active" : ""%>"
					href="S0030.html">アカウント登録</a></li>
				<li><a class="<%=uri.endsWith("C0040.jsp") ? "active" : ""%>"
					href="C0040.html">アカウント検索</a></li>
				<li><a class="logout right" href="logout.jsp">ログアウト</a></li>
			</ul>
		</nav>
	</header>

	<main class="container mt-5">
		<h1>アカウント条件検索表示</h1>

		<c:if test="${not empty noResult}">
			<div class="alert alert-warning">${noResult}</div>
		</c:if>
		<form action="C0040.html" method="post">

			<div class="mb-3">
				<label class="form-label">氏名 <span
					class="badge bg-secondary">部分一致</span></label> <input type="text"
					name="name" value="${sessionScope.searchName}" class="form-control"
					placeholder="氏名">
			</div>
			<div class="mb-3">
				<label class="form-label">メールアドレス</label> <input type="text"
					name="email" value="${sessionScope.searchEmail}"
					class="form-control" placeholder="メールアドレス">
			</div>


			<div class="mb-3">
				<label class="form-label d-block mb-1">権限</label>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="0"
						${fn:contains(sessionScope.searchAuthorities, '0') ? 'checked' : ''}>
					<label class="form-check-label">権限なし</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="1"
						${fn:contains(sessionScope.searchAuthorities, '1') ? 'checked' : ''}>
					<label class="form-check-label">売上登録</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="2"
						${fn:contains(sessionScope.searchAuthorities, '2') ? 'checked' : ''}>
					<label class="form-check-label">アカウント登録</label>
				</div>
			</div>


			<div class="form-group d-flex" style="margin-left: 210px;">
				<button type="submit" class="btn btn-primary me-2">検索</button>
				<button type="reset" class="btn btn-outline-secondary">クリア</button>
			</div>
		</form>
</body>
</html>
