<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>売上検索条件入力|物品売上管理システム</title>
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
	
	form {
		max-width: 700px;
		margin: 0 auto;
	}
	
	.wide-input {
		width: 400px;
	}
	.date-range-wrapper {
		display: flex;
		align-items: center;
		width: 400px;
	}

	.date-range-wrapper input[type="date"] {
		flex: 1;
	}
	
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
		<h1>売上検索条件入力</h1>
		<c:if test="${not empty errors}">
			<div class="alert alert-danger">
				<ul>
					<c:forEach var="err" items="${errors}">
						<li>${err}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<form action="S0020.html" method="post">
			<div class="form-group">
				<label for="inputSale_date_start">販売日</label>
				<div class="date-range-wrapper">
					<input type="date" class="form-control" id="inputSale_date_start" name="dateStart" value="${ saleSearchForm.dateStart }">
					<span class="mx-4">～</span>
					<input type="date" class="form-control" id="inputSale_date_end" name="dateEnd" value="${ saleSearchForm.dateEnd }">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAccount_id">担当</label>
				<select class="form-select wide-input" id="inputAccount_id" name="accountId">
					<option value="0" <c:if test="${ saleSearchForm.accountId == 0 }">selected</c:if>>選択してください</option>
					<c:forEach var="item" items="${ accounts }">
						<option value="${ item.accountId }" <c:if test="${ saleSearchForm.accountId == item.accountId }">selected</c:if>><c:out value="${ item.name }" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="inputCategory_id">商品カテゴリー</label>
				<select class="form-select wide-input" id="inputCategory_id" name="categoryId">
					<option value="0" <c:if test="${ saleSearchForm.categoryId == 0 }">selected</c:if>>選択してください</option>
					<c:forEach var="item" items="${ categories }">
						<option value="${ item.categoryId }" <c:if test="${ saleSearchForm.categoryId == item.categoryId }">selected</c:if>><c:out value="${ item.categoryName }" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="inputTrade_name">商品名 <span class="badge bg-secondary">部分一致</span> </label>
				<input type="text" class="form-control wide-input" id="inputTrade_name" name="tradeName" placeholder="商品名" value="${ saleSearchForm.tradeName }">
			</div>
			<div class="form-group note-group">
				<label for="inputNote" class="pt-10">備考 <span class="badge bg-secondary">部分一致</span> </label>
				<input type="text" class="form-control wide-input" id="inputNote" name="note" placeholder="備考" value="${ saleSearchForm.note }">
			</div>
			<div class="text-center mt-4">
				<button type="submit" class="btn btn-primary me-2">検索</button>
				<button type="reset" class="btn btn-outline-secondary">クリア</button>
			</div>
		</form>
	</main>
</body>
</html>