<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント登録</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css">
<script src="js/bootstrap.bundle.min.js"></script>
<style>
	
   form {
    max-width: 600px;
    margin: 0 auto;
  	}
  .alert ul {
		margin-bottom: 0;
		padding-left: 1.2rem;
	}
	.alert li {
		margin-bottom: 0;
	}
   @media (max-width: 768px) {
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
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>

	<main class="container mt-5">
	<h1>アカウント登録</h1>
	<div class="form-container">
	
	<%-- チェックボックスの選択状態を JSTL で処理するためのセットアップ --%>
	<c:set var="joinedAuthorities" value="${fn:join(authorities, ',')}" />
	<form action="S0030.html" method="post">
	<c:if test="${not empty errors}">
  	<div class="alert alert-danger" style="margin-bottom: 1em;">
    	<ul>
     		<c:forEach var="msg" items="${errors.values()}">
        		<li>${msg}</li>
      		</c:forEach>
    	</ul>
  	</div>
	</c:if>
	
		<div class="row align-items-center mb-3">
		<label for="name" class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center gap-1">
			氏名 <span class="badge bg-secondary">必須</span></label>
			 <div class="col-12 col-md-9">
       		 	<input type="text" name="name" id="name" class="form-control" value="${fn:escapeXml(name) != null ? fn:escapeXml(name) : ''}" placeholder="氏名">
      		</div>
    	</div>
		<div class="row align-items-center mb-3">
		<label for="mail" class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center gap-1" style="white-space: nowrap;">
			メールアドレス <span class="badge bg-secondary">必須</span></label>
        	<div class="col-12 col-md-9">
        		<input type="mail" name="mail" id="mail" class="form-control" value="${fn:escapeXml(mail) != null ? fn:escapeXml(mail) : ''}" placeholder="メールアドレス">
      		</div>
   		</div>
		<div class="row align-items-center mb-3">
		<label for="password" class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center gap-1">
			 パスワード <span class="badge bg-secondary">必須</span></label>
        	<div class="col-12 col-md-9">
        		<input type="password" name="password" id="password" class="form-control" value="${fn:escapeXml(password) != null ? fn:escapeXml(password) : ''}" placeholder="パスワード">
      		</div>
    	</div>
		<div class="row align-items-center mb-3">
		<label for="confirmPassword" class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center gap-1">
			パスワード（確認）<span class="badge bg-secondary">必須</span></label>
        	<div class="col-12 col-md-9">
        		<input type="password" name="confirmPassword" id="confirmPassword" class="form-control" value="${fn:escapeXml(confirmPassword) != null ? fn:escapeXml(confirmPassword) : ''}" placeholder="パスワード（確認）">
      		</div>
    	</div>
    	<c:set var="joinedAuthorities" value="${fn:join(authorities, ',')}" />
		<div class="row align-items-center mb-3">
		<label class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center">
			権限 <span class="badge bg-secondary">必須</span></label>
			<div class="col-12 col-md-9">
			
        	<div class="form-check form-check-inline">
          		<input class="form-check-input" type="checkbox" name="authorities" value="0" id="authNone" <c:if test="${fn:contains(joinedAuthorities, '0')}">checked</c:if>>
          		<label class="form-check-label" for="authNone">権限なし</label>
        	</div>
       		 <div class="form-check form-check-inline">
          		<input class="form-check-input" type="checkbox" name="authorities" value="1" id="authSales" <c:if test="${fn:contains(joinedAuthorities, '1')}">checked</c:if>>
          		<label class="form-check-label" for="authSales">売上登録</label>
        	</div>
        	<div class="form-check form-check-inline">
          		<input class="form-check-input" type="checkbox" name="authorities" value="2" id="authAccount" <c:if test="${fn:contains(joinedAuthorities, '2')}">checked</c:if>>
          		<label class="form-check-label" for="authAccount">アカウント登録</label>
        	</div>
      	</div>
    	</div>
    	<div class="row">
      		<div class="col-12 text-center">
        		<button type="submit" class="btn btn-primary">✔ 登録</button>
      		</div>
    	</div>
	</form>
	</main>
	
	<!-- 権限チェックボックスの相互制御 -->
<script>
	document.addEventListener("DOMContentLoaded", () => {
    const checkNone = document.getElementById("authNone");
    const checkSales = document.getElementById("authSales");
    const checkAccount = document.getElementById("authAccount");

 	// 状態に応じてチェックボックスの有効/無効を切り替える
    function updateUI() {
        const isNone = checkNone.checked;
        checkSales.disabled = isNone;
        checkAccount.disabled = isNone;
    }

 	// 「権限なし」が選択されたとき、他のチェックを外す
    checkNone.addEventListener("change", () => {
        if (checkNone.checked) {
            checkSales.checked = false;
            checkAccount.checked = false;
        }
        updateUI();
    });

 	// 他の権限が選択されたとき、「権限なし」のチェックを外す
    [checkSales, checkAccount].forEach(cb => {
        cb.addEventListener("change", () => {
            if (checkSales.checked || checkAccount.checked) {
                checkNone.checked = false;
            }
            updateUI();
        });
    });

 	// 初期状態の調整
    updateUI();
	});
</script>
	
</body>
</html>