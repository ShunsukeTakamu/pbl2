<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.SaleDetail" %>
<jsp:useBean id="detail" class="beans.SaleDetail" scope="request" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上詳細編集 | 物品売上管理システム</title>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="css/style.css">
	<style>
		.form-group { display: flex; align-items: center; margin-bottom: 1rem; }
		.form-group label { width: 200px; margin-right: 10px; text-align: right; }
		.note-group { align-items: flex-start; }
		.note-group label { margin-top: 6px; }
		.short-input { width: 150px; }
		.wide-input  { width: 300px; }
	</style>
</head>
<body>
	<header>
		<nav class="navbar">
			<div class="logo">物品売上管理システム</div>
			<ul class="nav-links">
				<li><a class="active" href="dashboard.jsp">ダッシュボード</a></li>
				<li><a href="sales_register.jsp">売上登録</a></li>
				<li><a href="sales_search.jsp">売上検索</a></li>
				<li><a href="account_register.jsp">アカウント登録</a></li>
				<li><a href="account_search.jsp">アカウント検索</a></li>
				<li><a href="logout.jsp" class="logout right">ログアウト</a></li>
			</ul>
		</nav>
	</header>

	<main class="container mt-5">
		<h1>売上詳細編集</h1>

		<!-- 編集内容を更新確認画面を挟まず即更新する場合はこのまま -->
		<form action="S0024.jsp" method="post">
			<input type="hidden" name="sale_id" value="${detail.saleId}">

			<!-- ▼ 販売日 -->
			<div class="form-group">
				<label for="inputSale_date">販売日 <span class="badge bg-secondary">必須</span></label>
				<input type="date" class="form-control short-input"
					   id="inputSale_date" name="sale_date"
					   value="${detail.saleDate}">
			</div>

			<!-- ▼ 担当（accounts テーブル） -->
			<div class="form-group">
				<label for="inputAccount_id">担当 <span class="badge bg-secondary">必須</span></label>
				<select class="form-select wide-input" id="inputAccount_id"
						name="account_id" required>
					<option value="" disabled hidden>選択してください</option>
					<c:forEach var="a" items="${accounts}">
						<option value="${a.accountId}"
								${a.accountId == detail.accountId ? 'selected' : ''}>
							${a.name}
						</option>
					</c:forEach>
				</select>
			</div>

			<!-- ▼ カテゴリー（categories テーブル） -->
			<div class="form-group">
				<label for="inputCategory_id">商品カテゴリー <span class="badge bg-secondary">必須</span></label>
				<select class="form-select wide-input" id="inputCategory_id"
						name="category_id" required>
					<option value="" disabled hidden>選択してください</option>
					<c:forEach var="c" items="${categories}">
						<option value="${c.categoryId}"
								${c.categoryId == detail.categoryId ? 'selected' : ''}>
							${c.categoryName}
						</option>
					</c:forEach>
				</select>
			</div>

			<!-- ▼ 商品名 -->
			<div class="form-group">
				<label for="inputTrade_name">商品名 <span class="badge bg-secondary">必須</span></label>
				<input type="text" class="form-control wide-input"
					   id="inputTrade_name" name="trade_name"
					   value="${detail.tradeName}">
			</div>

			<!-- ▼ 単価 -->
			<div class="form-group">
				<label for="inputUnit_price">単価 <span class="badge bg-secondary">必須</span></label>
				<input type="number" class="form-control short-input"
					   id="inputUnit_price" name="unit_price"
					   value="${detail.unitPrice}">
			</div>

			<!-- ▼ 個数 -->
			<div class="form-group">
				<label for="inputSale_number">個数 <span class="badge bg-secondary">必須</span></label>
				<input type="number" class="form-control short-input"
					   id="inputSale_number" name="sale_number"
					   value="${detail.saleNumber}">
			</div>

			<!-- ▼ 備考 -->
			<div class="form-group note-group">
				<label for="inputNote">備考</label>
				<textarea class="form-control wide-input" rows="5"
						  id="inputNote" name="note">${detail.note}</textarea>
			</div>

			<!-- ▼ ボタン -->
			<div class="form-group d-flex" style="margin-left: 210px;">
				<button type="submit" class="btn btn-primary me-2">✔ 更新</button>
				<button type="button" class="btn btn-outline-secondary"
						onclick="history.back()">キャンセル</button>
			</div>
		</form>
	</main>

	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
