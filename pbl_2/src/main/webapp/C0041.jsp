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
body {
	margin: 0;
	font-family: 'Segoe UI', sans-serif;
	background-color: #fff;
	color: #333;
}

.container {
	padding: 30px;
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

	<div class="container mt-4">
		<h3>アカウント検索結果表示</h3>
		<table class="table table-bordered table-hover mt-3">
			<thead class="table-light">
				<tr>
					<th>操作</th>
					<th>No</th>
					<th>氏名</th>
					<th>メールアドレス</th>
					<th>権限</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${accounts}">
					<tr>
						<td>
							<form action="C0042Servlet" method="get" style="display: inline;">
								<input type="hidden" name="id" value="${user.accountId}">
								<button type="submit" class="btn btn-sm btn-primary">✔
									編集</button>
							</form>
							<form action="C0044.jsp" method="get" style="display: inline;">
								<input type="hidden" name="id" value="${user.accountId}">
								<button type="submit" class="btn btn-sm btn-danger">✘
									削除</button>
							</form>
						</td>
						<td>${user.accountId}</td>
						<td>${user.name}</td>
						<td>${user.mail}</td>

						<td>${user.authorityLabel }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>
