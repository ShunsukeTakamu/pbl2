<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>売上検索結果表示</title>
    <!-- Bootstrapのスタイルシート -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

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
    <!-- ナビゲーションバー -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light px-4">
        <a class="navbar-brand" href="#">物品売上管理システム</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="dashboard.jsp">ダッシュボード</a></li>
                <li class="nav-item"><a class="nav-link" href="sales_register.jsp">売上登録</a></li>
                <li class="nav-item"><a class="nav-link active" href="#">売上検索</a></li>
                <li class="nav-item"><a class="nav-link" href="account_register.jsp">アカウント登録</a></li>
                <li class="nav-item"><a class="nav-link" href="account_search.jsp">アカウント検索</a></li>
            </ul>
            <span class="navbar-text">
                <a class="nav-link" href="logout.jsp">ログアウト</a>
            </span>
        </div>
    </nav>

    <!-- メインコンテンツ -->
    <div class="container mt-5">
        <!-- 見出し -->
        <h2 class="mb-4">売上検索結果表示</h2>

        <!-- テーブル表示（レスポンシブ対応） -->
        <div class="table-responsive">
            <table class="table custom-table align-middle w-100">
                <!-- テーブルのヘッダー行 -->
                <thead class="text-center">
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
                    <c:forEach var="sale" items="${saleList}">
                        <tr>
                            <!-- 詳細ボタン -->
                            <td class="text-center">
                                <form action="SaleDetailServlet" method="get">
                                    <input type="hidden" name="sale_id" value="${sale.saleId}">
                                    <button class="btn btn-primary btn-sm">✔ 詳細</button>
                                </form>
                            </td>

                            <!-- 各項目のデータ表示 -->
                            <td>${sale.saleId}</td>
                            <td>${sale.saleDate}</td>
                            <td>${sale.accountName}</td>
                            <td>${sale.categoryName}</td>
                            <td>${sale.tradeName}</td>
                            <td class="text-end">${sale.unitPrice}</td>
                            <td class="text-end">${sale.saleNumber}</td>
                            <td class="text-end">${sale.unitPrice * sale.saleNumber}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
