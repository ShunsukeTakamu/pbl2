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
		<h1>アカウント詳細編集</h1>



		<c:if test="${not empty successMessage }">
			<div class="alert alert-success" role="alert">${successMessage }</div>
		</c:if>
		<form action="C0042Servlet" method="post">
			<input type="hidden" name="accountId" value="${account.accountId}" />

			<div class="mb-3">
				<label class="form-label">氏名 <span
					class="badge bg-secondary">必須</span></label> <input type="text" name="name"
					class="form-control"
					value="${not empty param.name ? param.name : account.name}">
				<c:if test="${not empty errors.name}">
					<div class="text-danger">${errors.name}</div>
				</c:if>
			</div>

			<div class="mb-3">
				<label class="form-label">メールアドレス <span
					class="badge bg-secondary">必須</span></label> <input type="email"
					name="email" class="form-control"
					value="${not empty param.email ? param.email : account.mail}">
				<c:if test="${not empty errors.email}">
					<div class="text-danger">${errors.email}</div>
				</c:if>
			</div>

			<div class="mb-3">
				<label class="form-label">パスワード <span
					class="badge bg-secondary">必須</span></label> <input type="password"
					name="password" class="form-control">
				<c:if test="${not empty errors.password}">
					<div class="text-danger">${errors.password}</div>
				</c:if>
			</div>

			<div class="mb-3">
				<label class="form-label">パスワード（確認） <span
					class="badge bg-secondary">必須</span></label> <input type="password"
					name="passwordConfirm" class="form-control">
				<c:if test="${not empty errors.passwordConfirm}">
					<div class="text-danger">${errors.passwordConfirm}</div>
				</c:if>
			</div>

			<c:set var="authVal" value="${account.authority[0]}" />
			<c:set var="hasParam" value="${not empty paramAuthorities}" />

			<div class="mb-3">
				<label class="form-label">権限 </label>

				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="0" id="authNone"
						<c:if test="${hasParam and fn:contains(paramAuthorities, '0')}">checked</c:if>
						<c:if test="${not hasParam and authVal == 0}">checked</c:if>>
					<label class="form-check-label" for="authNone">権限なし</label>
				</div>

				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="1" id="authSales"
						<c:if test="${hasParam and fn:contains(paramAuthorities, '1')}">checked</c:if>
						<c:if test="${not hasParam and (authVal == 1 or authVal == 3)}">checked</c:if>>
					<label class="form-check-label" for="authSales">売上登録</label>
				</div>

				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="2" id="authAccount"
						<c:if test="${hasParam and fn:contains(paramAuthorities, '2')}">checked</c:if>
						<c:if test="${not hasParam and (authVal == 2 or authVal == 3)}">checked</c:if>>
					<label class="form-check-label" for="authAccount">アカウント登録</label>

				</div>
				<c:if test="${not empty errors.authorities}">
					<div class="text-danger">${errors.authorities}</div>
				</c:if>
			</div>




			<div class="form-group d-flex" style="margin-left: 210px;">
				<button type="submit" class="btn btn-primary me-2">更新</button>
				<button type="reset" class="btn btn-outline-secondary">クリア</button>
			</div>

		</form>
	</main>

	<script>
document.addEventListener("DOMContentLoaded", () => {
  const checkNone = document.getElementById("authNone");
  const checkSales = document.getElementById("authSales");
  const checkAccount = document.getElementById("authAccount");

  function updateUI() {
    if (checkNone.checked) {
      checkSales.checked = false;
      checkAccount.checked = false;
      checkSales.disabled = true;
      checkAccount.disabled = true;
    }else if(!checkSales.checked && !checkAccount.checked){
        checkSales.disabled = false;
        checkAccount.disabled = false;
        } else {
      checkSales.disabled = false;
      checkAccount.disabled = false;
    }
  }

  checkNone.addEventListener("change", updateUI);

  [checkSales, checkAccount].forEach(cb => {
    cb.addEventListener("change", () => {
      // いずれかが選択されたら「権限なし」を外す
      if (checkSales.checked || checkAccount.checked) {
        checkNone.checked = false;
      }
      updateUI(); // 状態を更新
    });
  });

  // 初期表示時に実行
  updateUI();
});
</script>


</body>
</html>
