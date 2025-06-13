<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.SaleDetail" %>
<jsp:useBean id="detail" class="beans.SaleDetail" scope="request" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上詳細削除確認 | 物品売上管理システム</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
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
				<li class="logout right"><a href="logout.jsp">ログアウト</a></li>
			</ul>
		</nav>
	</header>

	<main class="container mt-5">
		<h1>売上詳細削除確認</h1>

		<form action="S0025.html" method="post">
			<input type="hidden" name="sale_id" value="${detail.saleId}">

			<div class="form-group">
				<label>販売日</label>
				<input type="text" class="form-control short-input" value="${detail.saleDate}" disabled>
			</div>

			<div class="form-group">
				<label>担当</label>
				<input type="text" class="form-control wide-input" value="${detail.accountName}" disabled>
			</div>

			<div class="form-group">
				<label>商品カテゴリー</label>
				<input type="text" class="form-control wide-input" value="${detail.categoryName}" disabled>
			</div>

			<div class="form-group">
				<label>商品名</label>
				<input type="text" class="form-control wide-input" value="${detail.tradeName}" disabled>
			</div>

			<div class="form-group">
				<label>単価</label>
				<input type="text" class="form-control short-input" value="${detail.unitPrice}" disabled>
			</div>

			<div class="form-group">
				<label>個数</label>
				<input type="text" class="form-control short-input" value="${detail.saleNumber}" disabled>
			</div>

			<div class="form-group">
				<label>備考</label>
				<textarea class="form-control wide-input" rows="3" disabled>${detail.note}</textarea>
			</div>

			<div class="form-group d-flex" style="margin-left: 210px;">
				<button type="submit" class="btn btn-danger me-2">✘ OK（削除）</button>
				<button type="button" class="btn btn-outline-secondary" onclick="history.back()">キャンセル</button>
			</div>
		</form>
	</main>
</body>
</html>
