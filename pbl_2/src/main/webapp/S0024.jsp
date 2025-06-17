<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上詳細編集確認 | 物品売上管理システム</title>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/style.css">
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
		form {
			max-width: 700px;
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
	<header>
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>

	<main class="container mt-5">
		<h1>売上詳細編集確認</h1>

		<form action="S0024.html" method="post">
			<!-- Hidden: 値の保持 -->
			<input type="hidden" name="saleId" value="${ sale.saleId }">
			<input type="hidden" name="saleDate" value="${ sale.saleDate }">
			<input type="hidden" name="accountId" value="${ sale.accountId }">
			<input type="hidden" name="categoryId" value="${ sale.categoryId }">
			<input type="hidden" name="tradeName" value="${ sale.tradeName }">
			<input type="hidden" name="unitPrice" value="${ sale.unitPrice }">
			<input type="hidden" name="saleNumber" value="${ sale.saleNumber }">
			<input type="hidden" name="note" value="${ sale.note }">

			<div class="form-group">
				<label>販売日</label>
				<input type="date" class="form-control short-input" value="${ sale.saleDate }" disabled>
			</div>

			<div class="form-group">
				<label>担当</label>
				<select class="form-select wide-input" disabled>
					<option><c:out value="${ selectedAccount.name }" /></option>
				</select>
			</div>

			<div class="form-group">
				<label>商品カテゴリー</label>
				<select class="form-select wide-input" disabled>
					<option><c:out value="${ selectedCategory.categoryName }" /></option>
				</select>
			</div>

			<div class="form-group">
				<label>商品名</label>
				<input type="text" class="form-control wide-input" value="${ sale.tradeName }" disabled>
			</div>

			<div class="form-group">
				<label>単価</label>
				<fmt:formatNumber value="${ sale.unitPrice }" type="number" groupingUsed="true" var="formattedPrice" />
				<input type="number" class="form-control short-input" value="${ formattedPrice }" disabled>
			</div>

			<div class="form-group">
				<label>個数</label>
				<fmt:formatNumber value="${ sale.saleNumber }" type="number" groupingUsed="true" var="formattedNumber" />
				<input type="number" class="form-control short-input" value="${ formattedNumber }" disabled>
			</div>

			<div class="form-group note-group">
				<label>備考</label>
				<textarea class="form-control wide-input" rows="5" disabled><c:out value="${ sale.note }" /></textarea>
			</div>

			<div class="text-center mt-4">
				<button type="submit" class="btn btn-primary me-2">✔ OK</button>
				<button type="button" class="btn btn-outline-secondary" onclick="history.back()">キャンセル</button>
			</div>
		</form>
	</main>

	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
