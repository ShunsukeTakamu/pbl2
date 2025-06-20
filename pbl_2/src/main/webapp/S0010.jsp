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
	.form-group { display: flex; align-items: center; margin-bottom: 1rem; }
	.form-group label { width: 200px; margin-right: 10px; text-align: right; }	
	form { max-width: 700px; margin: 0 auto; }
	.note-group { align-items: flex-start; }
	.note-group label { margin-top: 6px; }
	.short-input { width: 150px; }
	.wide-input  { width: 300px; }
	
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
	<header>
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>
	<main class="container mt-5">
		<h1>売上登録</h1>
		<c:if test="${not empty errors}">
			<div class="alert alert-danger">
				<ul>
					<c:forEach var="err" items="${errors}">
						<li>${err.value}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<form action="S0010.html" method="post">

			<div class="form-group">
				<label for="inputSale_date">販売日 <span class="badge bg-secondary">必須</span> </label>
				<input type="date" class="form-control short-input" id="inputSale_date" name="saleDate" value="${ sale.saleDate }">
			</div>
			<div class="form-group">
				<label for="inputAccount_id">担当 <span class="badge bg-secondary">必須</span> </label>
				<select class="form-select wide-input" id="inputAccount_id" name="accountId">
					<option value="0" <c:if test="${ sale.accountId == 0 }">selected</c:if> hidden>選択してください</option>
					<c:forEach var="item" items="${ accounts }">
						<option value="${ item.accountId }" <c:if test="${ sale.accountId == item.accountId }">selected</c:if>><c:out value="${ item.name }" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="inputCategory_id">商品カテゴリー <span class="badge bg-secondary">必須</span> </label>
				<select class="form-select wide-input" id="inputCategory_id" name="categoryId">
					<option value="0" <c:if test="${ sale.categoryId == 0 }">selected</c:if> hidden>選択してください</option>
					<c:forEach var="item" items="${ categories }">
						<option value="${ item.categoryId }" <c:if test="${ sale.categoryId == item.categoryId }">selected</c:if>><c:out value="${ item.categoryName }" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="inputTrade_name">商品名 <span class="badge bg-secondary">必須</span> </label>
				<input type="text" class="form-control wide-input" id="inputTrade_name" name="tradeName" placeholder="商品名" value="${ sale.tradeName }">
			</div>
			<div class="form-group">
				<label for="inputUnit_price">単価 <span class="badge bg-secondary">必須</span> </label>
				<input type="number" class="form-control short-input" id="inputUnit_price" name="unitPrice" placeholder="単価" <c:if test="${ sale.unitPrice >= 0 }">value="${ sale.unitPrice }"</c:if>>
			</div>
			<div class="form-group">
				<label for="inputSale_number">個数 <span class="badge bg-secondary">必須</span> </label>
				<input type="number" class="form-control short-input" id="inputSale_number" name="saleNumber" placeholder="個数" <c:if test="${ sale.saleNumber >= 0 }">value="${ sale.saleNumber }"</c:if>>
			</div>
			<div class="form-group note-group">
				<label for="inputNote" class="pt-10">備考</label>
				<textarea type="text" class="form-control wide-input" rows="5" id="inputNote" name="note" placeholder="備考"><c:out value="${ sale.note }" /></textarea>
			</div>
			<div class="text-center mt-4">
				<input type="submit" class="btn btn-primary" value="✔ 登録" />
			</div>
		</form>
	</main>
</body>
</html>