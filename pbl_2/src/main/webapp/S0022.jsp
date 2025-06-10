<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.SaleDetail" %>
<jsp:useBean id="detail" class="beans.SaleDetail" scope="request" />
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>物品売上管理システム</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
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

    <main>
        <div class="container mt-5">
            <h2 class="mb-4">売上詳細表示</h2>

            <table class="table w-50 mx-auto">
                <tr><th class="fw-bold ps-4">販売日</th><td>${detail.saleDate}</td></tr>
                <tr><th class="fw-bold ps-4">担当</th><td>${detail.accountName}</td></tr>
                <tr><th class="fw-bold ps-4">商品カテゴリー</th><td>${detail.categoryName}</td></tr>
                <tr><th class="fw-bold ps-4">商品名</th><td>${detail.tradeName}</td></tr>
                <tr><th class="fw-bold ps-4">単価</th><td>${detail.unitPrice}</td></tr>
                <tr><th class="fw-bold ps-4">個数</th><td>${detail.saleNumber}</td></tr>
                <tr><th class="fw-bold ps-4">備考</th><td>${detail.note}</td></tr>
            </table>

            <div class="text-center mt-4">
                <form action="S0023.jsp" method="get" style="display: inline-block;">
                    <input type="hidden" name="sale_id" value="${detail.saleId}">
                    <button class="btn btn-primary">✔ 編集</button>
                </form>

                <form action="S0025.jsp" method="get" style="display: inline-block;">
                    <input type="hidden" name="sale_id" value="${detail.saleId}">
                    <button class="btn btn-danger">✘ 削除</button>
                </form>

                <form action="S0021.jsp" method="get" style="display: inline-block;">
                    <button class="btn btn-secondary">キャンセル</button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
