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
<
style>body {
	margin: 0;
	font-family: 'Segoe UI', sans-serif;
	background-color: #fff;
	color: #333;
}

.content {
	max-width: 600px;
	margin: 50px auto;
	padding: 30px;
	background-color: #fdfdfd;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
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

</style>
<meta charset="UTF-8">
<title>物品売上管理システム</title>

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
                <li href="logout.jsp" class="logout" class="right">ログアウト</li>
			</ul>
		</nav>
	</header>

	<div class="container mt-4">
		<h3>アカウント検索結果表示</h3>
		<table class="table table-bordered table-hover mt-3">
			<thred class="table-light">
			<tr>
				<th>操作</th>
				<th>No</th>
				<th>氏名</th>
				<th>メールアドレス</th>
				<th>権限</th>
			</tr>
			</thred>
			<tbody>
				<c:forEach var="user" items="${accounts }">
					<tr>
						<td>
							<form action="S0040Servlet" method="get" style="display: inline;">
								<input type="hidden" name="id" value="${accounts.id }"
									<button type="submit" class btn btn-sm btn-primary>編集</button>
							</form>
							<form action="S0040" method="get" style="display: inline;">
								<input type="hidden" name="id" value="${accounts.id }">
								<button type="submit" class="btn btn-sm btn-denger">削除</button>
							</form>
						</td>
						<td>${accounts.id }</td>
						<td>${accounts.name }</td>
						<td>${accounts.mail }</td>
						<td><c:choose>
								<c:when test="${accounts.id == 0}">権限なし</c:when>
								<c:when test="${accounts.id == 1}">売上登録</c:when>
								<c:when test="${accounts.id == 2}">アカウント登録</c:when>
							</c:choose></td>
					</tr>

				</c:forEach>


			</tbody>

		</table>
	</div>

</body>
</html>
