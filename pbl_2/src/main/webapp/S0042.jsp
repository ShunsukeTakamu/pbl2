<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント詳細編集 | 物品売上管理システム</title>
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
<script>
  document.addEventListener("DOMContentLoaded", function () {
    <c:if test="${not empty errorsList}">
      const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
      errorModal.show();
    </c:if>
  });
</script>

<body>
	<header>
		<!-- ナビバーのインクルード -->
		<jsp:include page="/navbar.jsp" />
	</header>
	<main class="container mt-5">
		<h1>アカウント詳細編集</h1>

		<c:if test="${not empty successMessage }">
			<div class="alert alert-success" role="alert">${successMessage }</div>
		</c:if>
		<c:if test="${not empty errorsList}">
			<div class="alert alert-danger" role="alert">
				<ul class="mb-0">
					<c:forEach var="err" items="${errorsList}">
						<li>${fn:escapeXml(err)}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<form action="S0042.html" method="post">
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
					value="${not empty param.email ? param.email : account.email}">
				<c:if test="${not empty errors.email}">
					<div class="text-danger">${errors.email}</div>
				</c:if>
			</div>

			<div class="mb-3">
				<label class="form-label">パスワード <span
					class="badge bg-secondary">必須</span></label>
				<div class="input-group">
					<input type="password" name="password" id="password"
						class="form-control">

				</div>
				<c:if test="${not empty errors.password}">
					<div class="text-danger">${errors.password}</div>
				</c:if>
			</div>

			<div class="mb-3">
				<label class="form-label">パスワード（確認） <span
					class="badge bg-secondary">必須</span></label>
				<div class="input-group">
					<input type="password" name="passwordConfirm" id="passwordConfirm"
						class="form-control">

				</div>
				<c:if test="${not empty errors.passwordConfirm}">
					<div class="text-danger">${errors.passwordConfirm}</div>
				</c:if>
			</div>

			<c:set var="joinedAuthorities"
				value="${fn:join(paramAuthorities, ',')}" />
			<c:set var="hasParam" value="${not empty paramAuthorities}" />

			<div class="mb-3">
				<label class="form-label">権限 </label>

				<!-- 権限なし -->
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="0" id="authNone"
						<c:if test="${hasParam and fn:contains(joinedAuthorities, '0')}">checked</c:if>
						<c:if test="${not hasParam and authVal == 0}">checked</c:if>>
					<label class="form-check-label" for="authNone">権限なし</label>
				</div>

				<!-- 売上登録 -->
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="1" id="authSales"
						<c:if test="${hasParam and fn:contains(joinedAuthorities, '1')}">checked</c:if>
						<c:if test="${not hasParam and (authVal == 1 or authVal == 3)}">checked</c:if>>
					<label class="form-check-label" for="authSales">売上登録</label>
				</div>

				<!-- アカウント登録 -->
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="2" id="authAccount"
						<c:if test="${hasParam and fn:contains(joinedAuthorities, '2')}">checked</c:if>
						<c:if test="${not hasParam and (authVal == 2 or authVal == 3)}">checked</c:if>>
					<label class="form-check-label" for="authAccount">アカウント登録</label>
				</div>

				<c:if test="${not empty errors.authorities}">
					<div class="text-danger">${errors.authorities}</div>
				</c:if>
			</div>

			<div class="form-group d-flex" style="margin-left: 210px;">
				<button type="submit" class="btn btn-primary me-2">更新</button>
				<a href="S0041.html" class="btn btn-outline-secondary">キャンセル</a>
			</div>

		</form>

	</main>

	<script>


document.addEventListener("DOMContentLoaded", () => {
  const checkNone = document.getElementById("authNone");
  const checkSales = document.getElementById("authSales");
  const checkAccount = document.getElementById("authAccount");

  function updateUI() {
    const isNone = checkNone.checked;
    const isSalesOrAccount = checkSales.checked || checkAccount.checked;

    checkSales.disabled = isNone;
    checkAccount.disabled = isNone;

    checkNone.disabled = isSalesOrAccount;
  }

  checkNone.addEventListener("change", () => {
    if (checkNone.checked) {
      checkSales.checked = false;
      checkAccount.checked = false;
    }
    updateUI();
  });

  [checkSales, checkAccount].forEach(cb => {
    cb.addEventListener("change", () => {
      if (checkSales.checked || checkAccount.checked) {
        checkNone.checked = false;
      }
      updateUI();
    });
  });

  updateUI();

  // 成功メッセージ自動非表示
  setTimeout(() => {
    const alert = document.getElementById("successAlert");
    if (alert) {
      alert.classList.remove('show');
    }
  }, 3000);
});
</script>



</body>
</html>
