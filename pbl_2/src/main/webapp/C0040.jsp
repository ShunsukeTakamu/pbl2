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
				<li><a href="logout.jsp" class="logout" class="right">ログアウト</a></li>
			</ul>
		</nav>
	</header>

	<div class="content">
		<form action="C0040Servlet" method="post">

			<div class="mb-3">
				<label class="form-label" >氏名 <span class="badge bg-secondary">部分一致</span></label> <input type="text" name="name"
					value="${param.name}" class="form-control" placeholder="氏名">
			</div>
			<div class="mb-3">
				<label class="form-label">メールアドレス</label> <input type="email"
					value="${param.email}" name="email" class="form-control"
					placeholder="メールアドレス">
			</div>


			<div class="mb-3">
				<label class="form-label d-block mb-1">権限</label>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="0"
						${fn:contains(paramValues['authorities'], '0') ?  'checked' : ''}>
					<label class="form-check-label">権限なし</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="1"
						${fn:contains(paramValues['authorities'], '1') ?  'checked' : ''}>
					<label class="form-check-label">売上登録</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="2"
						${fn:contains(paramValues['authorities'], '2') ?  'checked' : ''}>
					<label class="form-check-label">アカウント登録</label>
				</div>
			</div>


			<div class="btn-group">
				<button type="submit" class="btn btn-primary">検索</button>
				<button type="reset" class="btn btn-secondary">クリア</button>
			</div>
		</form>
	</div>

</body>
</html>
