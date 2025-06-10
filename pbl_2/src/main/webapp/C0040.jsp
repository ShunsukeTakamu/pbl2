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
<style>
body {
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

	<div class="content">
		<form action="AccountSearchServlet" method="get">

			<div class="mb-3">
				<label class="form-label">氏名</label> <input type="text" name="name"
					class="form-control" placeholder="氏名">
			</div>
			<div class="mb-3">
				<label class="form-label">メールアドレス</label> <input type="email"
					name="email" class="form-control" placeholder="メールアドレス">
			</div>
			<div class="mb-3">
	<label class="form-label d-block mb-1">権限</label>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="authority" id="auth0" value="0" checked>
		<label class="form-check-label" for="auth0">権限なし</label>
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="authority" id="auth1" value="1">
		<label class="form-check-label" for="auth1">売上登録</label>
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="authority" id="auth2" value="2">
		<label class="form-check-label" for="auth2">アカウント検索</label>
	</div>
</div>

<div class="btn-group">
	<button type="submit" class="btn btn-primary">検索</button>
	<button type="reset" class="btn btn-secondary">クリア</button>
</div>

	</div>

</body>
</html>
