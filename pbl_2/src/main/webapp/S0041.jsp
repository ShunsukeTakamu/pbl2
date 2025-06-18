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
<header>
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>
	<main class="container mt-5">
		<h1>アカウント検索結果表示</h1>
		<table class="table custom-table align-middle w-100">
			<thead class="table-light">

				<c:if test="${not empty update}">
					<div id="successAlert"
						class="alert alert-success alert-dismissible fade show"
						role="alert">${update}</div>
					<c:remove var="update" scope="session" />
				</c:if>

				<c:if test="${not empty delete}">
					<div id="deleteAlert"
						class="alert alert-danger alert-dismissible fade show"
						role="alert">${delete}</div>
					<c:remove var="delete" scope="session" />
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
	<c:choose>
		<c:when test="${empty accounts}">
			<tr>
				<td colspan="5" class="text-center text-muted">
					該当するアカウントがありませんでした。
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="user" items="${accounts}">
				<tr>
					<td>
						<form action="S0042.html" method="get" style="display: inline;">
							<input type="hidden" name="id" value="${user.accountId}">
							<button type="submit" class="btn btn-sm btn-primary">✔ 編集</button>
						</form>
						<form action="S0044.html" method="get" style="display: inline;">
							<input type="hidden" name="id" value="${user.accountId}">
							<button type="submit" class="btn btn-sm btn-danger">✘ 削除</button>
						</form>
					</td>
					<td>${user.accountId}</td>
					<td>${user.name}</td>
					<td>${user.mail}</td>
					<td>${user.authorityLabel}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</tbody>

		</table>
		</div>

		<script>
  // 3秒後にアラートをフェードアウトさせる
  setTimeout(() => {
    const successAlert = document.getElementById('successAlert');
    if (successAlert) {
      successAlert.classList.remove('show');
    }

    const deleteAlert = document.getElementById('deleteAlert');
    if (deleteAlert) {
      deleteAlert.classList.remove('show');
    }
  }, 3000); // 3000ミリ秒 = 3秒
</script>
</body>
</html>
