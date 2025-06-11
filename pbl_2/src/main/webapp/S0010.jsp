<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上登録|物品売上管理システム</title>
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
	
	.form-group {
		flex: 1;
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
		<nav class="navbar">
			<div class="logo">物品売上管理システム</div>
			<ul class="nav-links">
				<li><a class="active" href="dashboard.jsp">ダッシュボード</a></li>
				<li><a href="sales_register.jsp">売上登録</a></li>
				<li><a href="sales_search.jsp">売上検索</a></li>
				<li><a href="account_register.jsp">アカウント登録</a></li>
				<li><a href="account_search.jsp">アカウント検索</a></li>
				<li href="logout.jsp" class="logout" class="right">ログアウト</li>
			</ul>
		</nav>
	</header>
	<main class="container mt-5">
		<h1>売上登録</h1>
		<form action="S0010Servlet" method="post">

			<div class="form-group">
				<label for="inputSale_date">販売日 <span class="badge bg-secondary">必須</span> </label>
				<input type="date" class="form-control short-input" id="inputSale_date" name="saleDate" value="${ today }">
			</div>
			<div class="form-group">
				<label for="inputAccount_id">担当 <span class="badge bg-secondary">必須</span> </label>
				<select class="form-select wide-input" id="inputAccount_id" name="accountId">
					<option value="0" selected hidden>選択してください</option>
					<c:forEach var="item" items="${ accounts }">
						<option value="${ item.getAccountId() }"><c:out value="${ item.getName() }" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="inputCategory_id">商品カテゴリー <span class="badge bg-secondary">必須</span> </label>
				<select class="form-select wide-input" id="inputCategory_id" name="categoryId">
					<option value="0" selected hidden>選択してください</option>
					<c:forEach var="item" items="${ categories }">
						<option value="${ item.getCategoryId() }"><c:out value="${ item.getCategoryName() }" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="inputTrade_name">商品名 <span class="badge bg-secondary">必須</span> </label>
				<input type="text" class="form-control wide-input" id="inputTrade_name" name="tradeName" placeholder="商品名" value="">
			</div>
			<div class="form-group">
				<label for="inputUnit_price">単価 <span class="badge bg-secondary">必須</span> </label>
				<input type="text" class="form-control short-input" id="inputUnit_price" name="unitPrice" placeholder="単価">
			</div>
			<div class="form-group">
				<label for="inputSale_number">個数 <span class="badge bg-secondary">必須</span> </label>
				<input type="text" class="form-control short-input" id="inputSale_number" name="saleNumber" placeholder="個数">
			</div>
			<div class="form-group note-group">
				<label for="inputNote" class="pt-10">備考</label>
				<textarea type="text" class="form-control wide-input" rows="5" id="inputNote" name="note" placeholder="備考"></textarea>
			</div>
			<div class="form-group d-flex" style="margin-left: 210px;">
				<input type="submit" class="btn btn-primary" value="✔登 録" />
			</div>
		</form>
	</main>
</body>
</html>