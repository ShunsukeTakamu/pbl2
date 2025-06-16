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
	<%
	String uri = request.getRequestURI();
	%>
	<header>
		<nav class="navbar">
			<div class="logo">物品売上管理システム</div>
			<ul class="nav-links">
				<li><a class="<%=uri.endsWith("C0020.jsp") ? "active" : ""%>"
					href="C0020.html">ダッシュボード</a></li>
				<li><a class="<%=uri.endsWith("S0010.jsp") ? "active" : ""%>"
					href="S0010.html">売上登録</a></li>
				<li><a class="<%=uri.endsWith("S0020.jsp") ? "active" : ""%>"
					href="S0020.html">売上検索</a></li>
				<li><a class="<%=uri.endsWith("S0030.jsp") ? "active" : ""%>"
					href="S0030.html">アカウント登録</a></li>
				<li><a class="<%=uri.endsWith("C0040.jsp") ? "active" : ""%>"
					href="C0040.html">アカウント検索</a></li>
				<li class="logout"><a href="logout.jsp">ログアウト</a></li>
			</ul>
		</nav>
	</header>

	<main class="container mt-5">
		<h1>売上詳細編集確認</h1>

		<form action="S0024.html" method="post">
			<!-- Hidden: 値の保持 -->
			<input type="hidden" name="sale_id" value="${param.sale_id}">
			<input type="hidden" name="sale_date" value="${param.sale_date}">
			<input type="hidden" name="account_id" value="${param.account_id}">
			<input type="hidden" name="category_id" value="${param.category_id}">
			<input type="hidden" name="trade_name" value="${param.trade_name}">
			<input type="hidden" name="unit_price" value="${param.unit_price}">
			<input type="hidden" name="sale_number" value="${param.sale_number}">
			<input type="hidden" name="note" value="${param.note}">

			<div class="form-group">
				<label>販売日</label>
				<input type="date" class="form-control short-input" value="${param.sale_date}" disabled>
			</div>

			<div class="form-group">
				<label>担当</label>
				<select class="form-select wide-input" disabled>
					<option>${sale.accountName}</option>
				</select>
			</div>

			<div class="form-group">
				<label>商品カテゴリー</label>
				<select class="form-select wide-input" disabled>
					<option>${sale.categoryName}</option>
				</select>
			</div>

			<div class="form-group">
				<label>商品名</label>
				<input type="text" class="form-control wide-input" value="${param.trade_name}" disabled>
			</div>

			<div class="form-group">
				<label>単価</label>
				<fmt:formatNumber value="${param.unit_price}" type="number" groupingUsed="true" var="formattedPrice" />
				<input type="text" class="form-control short-input" value="${formattedPrice}" disabled>
			</div>

			<div class="form-group">
				<label>個数</label>
				<fmt:formatNumber value="${param.sale_number}" type="number" groupingUsed="true" var="formattedNumber" />
				<input type="text" class="form-control short-input" value="${formattedNumber}" disabled>
			</div>

			<div class="form-group note-group">
				<label>備考</label>
				<textarea class="form-control wide-input" rows="5" disabled>${param.note}</textarea>
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
