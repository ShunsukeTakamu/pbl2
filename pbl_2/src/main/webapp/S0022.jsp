<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>売上詳細表示 | 物品売上管理システム</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
	.note-cell { word-break: break-word; max-width: 400px; }
	
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

    <main>
        <div class="container mt-5">
            <h1 class="mb-4">売上詳細表示</h1>
            
            <c:if test="${ not empty errors }">
				<div class="alert alert-danger">
					<ul>
						<c:forEach var="err" items="${ errors }">
							<li>${ err }</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
            
            <!-- データのフォーマットを整える -->
			<fmt:formatNumber value="${ sale.unitPrice }" type="number" groupingUsed="true" var="formattedPrice" />
			<fmt:formatNumber value="${ sale.saleNumber }" type="number" groupingUsed="true" var="formattedNumber" />
            <table class="table w-50 mx-auto">
                <tr><th class="fw-bold ps-4">販売日</th><td>${ formattedDate }</td></tr>
                <tr><th class="fw-bold ps-4">担当</th><td>${ accountName }</td></tr>
                <tr><th class="fw-bold ps-4">商品カテゴリー</th><td>${ categoryName }</td></tr>
                <tr><th class="fw-bold ps-4">商品名</th><td>${ sale.tradeName }</td></tr>
                <tr><th class="fw-bold ps-4">単価</th><td>${ formattedPrice }</td></tr>
                <tr><th class="fw-bold ps-4">個数</th><td>${ formattedNumber }</td></tr>
                <tr><th class="fw-bold ps-4">備考</th><td class="note-cell">${ sale.note }</td></tr>
            </table>

            <div class="text-center mt-4">

                <form action="S0022.html" method="post" style="display: inline-block;">
                    <input type="hidden" name="saleId" value="${ sale.saleId }">
                    <button name="button" value="edit" class="btn btn-primary">✔ 編集</button>
                </form>

                <!-- 削除ボタン -->
                <form action="S0022.html" method="post" style="display: inline-block;">
                    <input type="hidden" name="saleId" value="${ sale.saleId }">
                    <button name="button" value="delete"class="btn btn-danger">✘ 削除</button>
                </form>

                <!-- キャンセルボタン -->
                <form action="S0021.html" method="get" style="display: inline-block;">
                    <button class="btn btn-outline-secondary">キャンセル</button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
