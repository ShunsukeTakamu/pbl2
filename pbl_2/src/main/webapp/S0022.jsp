<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="beans.SaleDetail" %>
<jsp:useBean id="detail" class="beans.SaleDetail" scope="request" />

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>売上詳細表示 | 物品売上管理システム</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">

    
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
				<li><a class="logout right" href="logout.jsp">ログアウト</a></li>
			</ul>
		</nav>
	</header>

    <main>
        <div class="container mt-5">
            <h2 class="mb-4">売上詳細表示</h2>
            
            <!-- データのフォーマットを整える -->
			<fmt:formatNumber value="${ detail.unitPrice }" type="number" groupingUsed="true" var="formattedPrice" />
			<fmt:formatNumber value="${ detail.saleNumber }" type="number" groupingUsed="true" var="formattedNumber" />
            <table class="table w-50 mx-auto">
                <tr><th class="fw-bold ps-4">販売日</th><td>${formattedDate}</td></tr>
                <tr><th class="fw-bold ps-4">担当</th><td>${detail.accountName}</td></tr>
                <tr><th class="fw-bold ps-4">商品カテゴリー</th><td>${detail.categoryName}</td></tr>
                <tr><th class="fw-bold ps-4">商品名</th><td>${detail.tradeName}</td></tr>
                <tr><th class="fw-bold ps-4">単価</th><td>${formattedPrice}</td></tr>
                <tr><th class="fw-bold ps-4">個数</th><td>${formattedNumber}</td></tr>
                <tr><th class="fw-bold ps-4">備考</th><td>${detail.note}</td></tr>
            </table>

            <div class="text-center mt-4">

                <form action="S0023.html" method="get" style="display: inline-block;">
                    <input type="hidden" name="sale_id" value="${detail.saleId}">
                    <button class="btn btn-primary">✔ 更新</button>
                </form>

                <!-- 削除ボタン -->
                <form action="S0025Loader.html" method="get" style="display: inline-block;">
                    <input type="hidden" name="sale_id" value="${detail.saleId}">
                    <button class="btn btn-danger">✘ 削除</button>
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
