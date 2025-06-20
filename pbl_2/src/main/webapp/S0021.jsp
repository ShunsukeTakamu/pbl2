<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>売上検索結果表示</title>
<!-- Bootstrapのスタイルシート -->
<link  rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">

<!-- 表の上下に線だけ引くためのカスタムスタイル -->
<style>
.custom-table thead {
	border-top: 2px solid #dee2e6;
	border-bottom: 2px solid #dee2e6;
}

.custom-table tbody tr {
	border-bottom: 1px solid #dee2e6;
}
</style>
</head>

<body>
	<header>
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>

	<!-- メインコンテンツ -->
	<div class="container mt-5">
		<!-- 見出し -->
		<h1 class="mb-4">売上検索結果表示</h1>

		<!-- テーブル表示（レスポンシブ対応） -->
		<div class="table-responsive">
			<table class="table custom-table align-middle w-100">
				<!-- テーブルのヘッダー行 -->
				<thead class="table-light">
					<tr>
						<th>操作</th>
						<th>No</th>
						<th>販売日</th>
						<th>担当</th>
						<th>商品カテゴリー</th>
						<th>商品名</th>
						<th>単価</th>
						<th>個数</th>
						<th>小計</th>
					</tr>
				</thead>

				<!-- データ行 -->
				<tbody>
					<c:forEach var="sale" items="${ saleList }" varStatus="stt">
						<tr>
							<!-- 詳細ボタン -->
							<td class="text-left">
                                <form action="S0022.html" method="get">
                                    <input type="hidden" name="saleId"
                                        value="${ sale.saleId }">
                                    <button class="btn btn-primary btn-sm">✔ 詳細</button>
                                </form>
							</td>

							<!-- データのフォーマットを整える -->
							<fmt:formatNumber value="${ sale.unitPrice }" type="number" groupingUsed="true" var="formattedPrice" />
							<fmt:formatNumber value="${ sale.saleNumber }" type="number" groupingUsed="true" var="formattedNumber" />
							<fmt:formatNumber value="${ sale.unitPrice * sale.saleNumber }" type="number" groupingUsed="true" var="formattedSubTotal" />
							<!-- 各項目のデータ表示 -->
							<td>${ sale.saleId }</td>
							<td>${ formattedDates.get(stt.index) }</td>
							<td>${ accountMap.get(sale.accountId) }</td>
							<td>${ categoryMap.get(sale.categoryId) }</td>
							<td>${ sale.tradeName }</td>
							<td>${ formattedPrice }</td>
							<td>${ formattedNumber }</td>
							<td>${ formattedSubTotal }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
