<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="errors" class="java.util.ArrayList" scope="request" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上詳細編集 | 物品売上管理システム</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<style>
		.form-group { display: flex; align-items: center; margin-bottom: 1rem; }
		.form-group label { width: 200px; margin-right: 10px; text-align: right; }
		form { max-width: 700px; margin: 0 auto; }
		.note-group { align-items: flex-start; }
		.note-group label { margin-top: 6px; }
		.short-input { width: 150px; }
		.wide-input  { width: 300px; }
		.text-danger { color: red; font-size: 0.9rem; margin-left: 10px; }
		
		/* ▼ エラー赤枠調整 */
		.alert ul {
			margin-bottom: 0;
			padding-left: 1.2rem;
		}
		.alert li {
			margin-bottom: 0;
		}
		/* エラー赤枠調整 */
		
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
		<h1>売上詳細編集</h1>

		<c:if test="${ not empty errors }">
			<div class="alert alert-danger w-75">
				<ul>
					<c:forEach var="err" items="${ errors }">
						<li>${ err }</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<form action="S0023Confirm.html" method="post">
			<input type="hidden" name="saleId" value="${ detail.saleId }">

			<!-- 販売日 -->
			<div class="form-group">
				<label for="inputSale_date">販売日</label>
				<input type="date" class="form-control short-input" id="inputSale_date" name="saleDate" value="${ detail.saleDate }">
			</div>

			<!-- 担当 -->
			<div class="form-group">
				<label for="inputAccount_id">担当</label>
				<select class="form-select wide-input" id="inputAccount_id" name="accountId">
					<option value="">選択してください</option>
					<c:forEach var="a" items="${ accounts }">
						<option value="${ a.accountId }" ${ a.accountId == detail.accountId ? 'selected' : '' }>${ a.name }</option>
					</c:forEach>
				</select>
				
			</div>

			<!-- 商品カテゴリー -->
			<div class="form-group">
				<label for="inputCategory_id">商品カテゴリー</label>
				<select class="form-select wide-input" id="inputCategory_id" name="categoryId">
					<option value="">選択してください</option>
					<c:forEach var="c" items="${ categories }">
						<option value="${ c.categoryId }" ${ c.categoryId == detail.categoryId ? 'selected' : '' }>${ c.categoryName }</option>
					</c:forEach>
				</select>
			</div>

			<!-- 商品名 -->
			<div class="form-group">
				<label for="inputTrade_name">商品名</label>
				<input type="text" class="form-control wide-input" id="inputTrade_name" name="tradeName" placeholder="商品名" value="${ detail.tradeName }">
			</div>

			<!-- 単価 -->
			<div class="form-group">
				<label for="inputUnit_price">単価</label>
				<input type="number" class="form-control short-input" id="inputUnit_price" name="unitPrice" placeholder="単価" <c:if test="${ detail.unitPrice >= 0 }">value="${detail.unitPrice}"</c:if>>
			</div>

			<!-- 個数 -->
			<div class="form-group">
				<label for="inputSale_number">個数</label>
				<input type="number" class="form-control short-input" id="inputSale_number" name="saleNumber" placeholder="個数" <c:if test="${ detail.saleNumber >= 0 }">value="${detail.saleNumber}"</c:if>>
			</div>

			<!-- 備考 -->
			<div class="form-group note-group">
				<label for="inputNote">備考</label>
				<textarea class="form-control wide-input" rows="5" id="inputNote" name="note" placeholder="備考">${ detail.note }</textarea>
			</div>

			<div class="text-center mt-4">
				<button type="submit" class="btn btn-primary me-2">✔ 更新</button>
				<a href="S0022.html?saleId=${ detail.saleId }" class="btn btn-outline-secondary">キャンセル</a>
			</div>
		</form>
	</main>
</body>
</html>
