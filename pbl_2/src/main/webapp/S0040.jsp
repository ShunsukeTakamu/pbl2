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
form {
	max-width: 600px;
	margin: 0 auto;
}

@media ( max-width : 768px) {
	.col-md-3, .col-md-9 {
		width: 100% !important;
	}
	.col-md-3 {
		text-align: left !important;
		margin-bottom: 0.25rem;
	}
}
</style>
</head>
<body>
	<header>
		<jsp:include page="/navbar.jsp" />
	</header>

	<main class="container mt-5">
		<h1>アカウント条件検索表示</h1>

		<!-- エラー表示 -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				${error}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<c:if test="${not empty noResult}">
			<div class="alert alert-warning alert-dismissible fade show"
				role="alert">
				${noResult}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<form action="S0040.html" method="post">
			<!-- 氏名 -->
			<div class="row align-items-center mb-3">
				<label for="name"
					class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center gap-1"
					style="white-space: nowrap;"> 氏名 <span
					class="badge bg-secondary">部分一致</span>
				</label>
				<div class="col-12 col-md-9">
					<input type="text" name="name" id="name" class="form-control"
						value="${sessionScope.searchName}" placeholder="氏名">
				</div>
			</div>

			<!-- メールアドレス -->
			<div class="row align-items-center mb-3">
				<label for="email"
					class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center gap-1"
					style="white-space: nowrap;"> メールアドレス <span
					class="badge bg-secondary">部分一致</span>
				</label>
				<div class="col-12 col-md-9">
					<input type="text" name="email" id="email" class="form-control"
						value="${sessionScope.searchEmail}" placeholder="メールアドレス">
				</div>
			</div>

			<!-- 権限 -->
			<div class="row align-items-center mb-3">
				<label
					class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center"
					style="white-space: nowrap;"> 権限 </label>
				<div class="col-12 col-md-9">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" id="authNone"
							name="authorities" value="0"
							<c:if test="${fn:contains(sessionScope.searchAuthorities, '0')}">checked</c:if>>
						<label class="form-check-label" for="authNone">権限なし</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" id="authSales"
							name="authorities" value="1"
							<c:if test="${fn:contains(sessionScope.searchAuthorities, '1')}">checked</c:if>>
						<label class="form-check-label" for="authSales">売上登録</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" id="authAccount"
							name="authorities" value="2"
							<c:if test="${fn:contains(sessionScope.searchAuthorities, '2')}">checked</c:if>>
						<label class="form-check-label" for="authAccount">アカウント登録</label>
					</div>
				</div>
			</div>

			<!-- ボタン -->
			<div class="row">
				<div class="col-12 text-center">
					<!-- 検索ボタン -->
					<button type="submit" name="action" value="search"
						class="btn btn-primary me-2">検索</button>

					<!-- クリアボタン（typeをsubmitに変更、JS不要） -->
					<button type="submit" name="action" value="clear"
						class="btn btn-outline-secondary">クリア</button>

				</div>
			</div>
		</form>
	</main>

	<!-- ✅ 排他的チェック制御スクリプト -->
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

      updateUI(); // 初期状態を正しく反映
    });

  </script>
</body>
</html>
