<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>物品売上管理システム</title>

    <!-- Materialize CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet">

    <!-- Google Icon Font -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    
    <style>
        .brand-logo {
            margin-left: 20px;
        }
        .logout-link {
            margin-right: 20px;
        }
    </style>
</head>
<body>

    <!-- ナビゲーションバー -->
    <nav class="blue">
        <div class="nav-wrapper">
            <a href="#" class="brand-logo">物品売上管理システム</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="C0020.jsp">ダッシュボード</a></li>
                <li><a href="S0010.jsp">売上登録</a></li>
                <li><a href="S0020.jsp">売上検索</a></li>
                <li><a href="S0030.jsp">アカウント登録</a></li>
                <li><a href="C0040.jsp">アカウント検索</a></li>
                <li><a href="logout.jsp" class="logout-link">ログアウト</a></li>
            </ul>
        </div>
    </nav>

    <!-- メイン内容 -->
    <main class="container">
        <h4 class="center-align">ダッシュボード</h4>

        <div class="row">
            <!-- 売上登録カード -->
            <div class="col s12 m6">
                <div class="card blue lighten-4">
                    <div class="card-content">
                        <span class="card-title">売上登録</span>
                        <p>本日の売上件数：10件</p>
                    </div>
                    <div class="card-action">
                        <a href="S0010.jsp">売上登録へ</a>
                    </div>
                </div>
            </div>

            <!-- アカウント登録カード -->
            <div class="col s12 m6">
                <div class="card green lighten-4">
                    <div class="card-content">
                        <span class="card-title">アカウント登録</span>
                        <p>現在の登録アカウント数：30人</p>
                    </div>
                    <div class="card-action">
                        <a href="S0030.jsp">アカウント登録へ</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- Materialize JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
