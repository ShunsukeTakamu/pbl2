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
.custom-table thead {
	border-top: 2px solid #dee2e6;
	border-bottom: 2px solid #dee2e6;
}

.custom-table tbody tr {
	border-bottom: 1px solid #dee2e6;
}

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

	<%
	String uri = request.getRequestURI();
	%>
	<header>
		<nav class="navbar">
			<div class="logo">物品売上管理システム</div>
			<ul class="nav-links">
				<li><a class="<%=uri.endsWith("C0020.jsp") ? "active" : ""%>"
					href="C0020Servlet">ダッシュボード</a></li>
				<li><a class="<%=uri.endsWith("S0010.jsp") ? "active" : ""%>"
					href="S0010Servlet">売上登録</a></li>
				<li><a class="<%=uri.endsWith("S0020.jsp") ? "active" : ""%>"
					href="S0020Servlet">売上検索</a></li>
				<li><a class="<%=uri.endsWith("S0030.jsp") ? "active" : ""%>"
					href="S0030Servlet">アカウント登録</a></li>
				<li><a class="<%=uri.endsWith("C0040.jsp") ? "active" : ""%>"
					href="C0040Servlet">アカウント検索</a></li>
				<li><a class="logout right" href="logout.jsp">ログアウト</a></li>
			</ul>
		</nav>
	</header>

	<main class="container mt-5">
		<h1>アカウント検索結果表示</h1>
		<table class="table custom-table align-middle w-100">
			<thead class="table-light">
				<c:if test="${not empty success }">
					<div class="alert alert-success" role="alert">${success}</div>
				</c:if>
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
							<form action="C0044Servlet" method="get" style="display: inline;">
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
