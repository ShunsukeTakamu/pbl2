<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		form {
			max-width: 700px;
			margin: 0 auto;
		}
		.short-input { width: 150px; }
		.wide-input  { width: 300px; }
	</style>
</head>
<body>
	<header>
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>

	<main class="container mt-5">
		<h1>売上詳細削除確認</h1>
		
		<c:if test="${ not empty errors }">
			<div class="alert alert-danger">
				<ul>
					<c:forEach var="err" items="${ errors }">
						<li>${ err }</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		
		<form action="S0025.html" method="post">
			<input type="hidden" name="saleId" value="${ sale.saleId }">

			<div class="form-group">
				<label>販売日</label>
				<input type="text" class="form-control short-input" value="${ formattedSaleDate }" disabled>
			</div>

			<div class="form-group">
				<label>担当</label>
				<input type="text" class="form-control wide-input" value="${ selectedAccount.name }" disabled>
			</div>

			<div class="form-group">
				<label>商品カテゴリー</label>
				<input type="text" class="form-control wide-input" value="${ selectedCategory.categoryName }" disabled>
			</div>

			<div class="form-group">
				<label>商品名</label>
				<input type="text" class="form-control wide-input" value="${ sale.tradeName }" disabled>
			</div>

			<div class="form-group">
				<label>単価</label>
				<fmt:formatNumber value="${sale.unitPrice}" type="number" groupingUsed="true" var="formattedPrice" />
				<input type="text" class="form-control short-input" value="${ formattedPrice }" disabled>
			</div>

			<div class="form-group">
				<label>個数</label>
				<fmt:formatNumber value="${sale.saleNumber}" type="number" groupingUsed="true" var="formattedNumber" />
				<input type="text" class="form-control short-input" value="${ formattedNumber }" disabled>
			</div>

			<div class="form-group">
				<label>備考</label>
				<textarea class="form-control wide-input" rows="3" disabled><c:out value="${ sale.note }" /></textarea>
			</div>

			<div class="text-center mt-4">
				<button type="submit" class="btn btn-danger me-2">✘ OK（削除）</button>
				<button type="button" class="btn btn-outline-secondary" onclick="history.back()">キャンセル</button>
			</div>
		</form>
	</main>
</body>
</html>
