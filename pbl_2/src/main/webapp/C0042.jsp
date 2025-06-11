<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<script src="js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>物品売上管理システム</title>
<style>
.text-danger {
	color: red;
	font-size: 0.9em;
}

.content {
	max-width: 600px;
	margin: 50px auto;
	padding: 30px;
	background: #fdfdfd;
	border-radius: 8px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.btn-group {
	text-align: center;
}
</style>
</head>
<body>
	<div class="content">
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

			<div class="mb-3">
				<label class="form-label">権限</label>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="authority"
						value="0" id="authNone"
						${param.authority == '0' || account.authority[0] == 0 ? 'checked' : ''}>
					<label class="form-check-label" for="authNone">権限なし</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="1" id="authSales"
						<c:if test="${fn:contains(paramValues['authorities'], '1')}">checked</c:if>
						<c:if test="${empty paramValues['authorities'] and (account.authority[0] == 1 or account.authority[0] == 3)}">checked</c:if>>
					<label class="form-check-label" for="authSales">売上登録</label>
				</div>

				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="authorities"
						value="2" id="authAccount"
						<c:if test="${fn:contains(paramValues['authorities'], '2')}">checked</c:if>
						<c:if test="${empty paramValues['authorities'] and (account.authority[0] == 2 or account.authority[0] == 3)}">checked</c:if>>
					<label class="form-check-label" for="authAccount">アカウント登録</label>
				</div>

			</div>

			<div class="btn-group">
				<button type="submit" class="btn btn-primary">更新</button>
				<button type="reset" class="btn btn-secondary">キャンセル</button>
			</div>
		</form>
	</div>

	<script>
    document.addEventListener("DOMContentLoaded", () => {
      const radioNone = document.getElementById("authNone");
      const checks = [document.getElementById("authSales"), document.getElementById("authAccount")];

      function toggle() {
        const disabled = radioNone.checked;
        checks.forEach(cb => {
          cb.disabled = disabled;
          if (disabled) cb.checked = false;
        });
      }

      radioNone.addEventListener("change", toggle);
<!--      初期反映-->
      toggle();
    });
  </script>
</body>
</html>
