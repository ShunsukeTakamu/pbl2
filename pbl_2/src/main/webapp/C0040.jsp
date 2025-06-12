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

body {
	margin: 0;
	font-family: 'Segoe UI', sans-serif;
	background-color: #fff;
	color: #333;
}

.content {
	max-width: 500px;
	margin: 50px auto;
	padding: 30px;


	border-radius: 8px;
}

form .mb-3 {
	margin-bottom: 1.5rem;
}

button {
	margin-right: 10px;
}

button:last-child {
	margin-right: 0;
}

.btn-group {
	text-align: center;
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
					href="C0020.jsp">ダッシュボード</a></li>
				<li><a class="<%=uri.endsWith("S0010.jsp") ? "active" : ""%>"
					href="S0010.jsp">売上登録</a></li>
				<li><a class="<%=uri.endsWith("S0020.jsp") ? "active" : ""%>"
					href="S0020.jsp">売上検索</a></li>
				<li><a class="<%=uri.endsWith("S0030.jsp") ? "active" : ""%>"
					href="S0030.jsp">アカウント登録</a></li>
				<li><a class="<%=uri.endsWith("C0040.jsp") ? "active" : ""%>"
					href="C0040.jsp">アカウント検索</a></li>
				<li><a class="logout right" href="logout.jsp">ログアウト</a></li>
			</ul>
		</nav>
	</header>
	

<main class="container mt-5">
	<div class="content">
<h3 class="mb-4 page-title">アカウント条件検索表示</h3>
		<form action="C0040Servlet" method="post">

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
	</div>

</body>
</html>
