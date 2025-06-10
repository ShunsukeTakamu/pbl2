<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	</style>
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
				<li href="logout.jsp" class="logout" class="right">ログアウト</li>
			</ul>
		</nav>
	</header>
	<main class="container mt-5">
		<h1>売上検索条件入力</h1>
		<form action="" method="post">
			<div class="form-group">
				<label for="inputSale_date_from">販売日</label>
				<div class="date-range-wrapper">
					<input type="date" class="form-control" id="inputSale_date_from" name="sale_date_from">
					<span class="mx-4">～</span>
					<input type="date" class="form-control" id="inputSale_date_to" name="sale_date_to">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAccount_id">担当</label>
				<select class="form-select wide-input" id="inputAccount_id">
					<option value="" disabled selected hidden>選択してください</option>
					<option value="1">佐藤</option>
					<option value="2">加藤</option>
					<option value="3">小澤</option>
					<option value="4">山本</option>
					<option value="5">藤堂</option>
				</select>
			</div>
			<div class="form-group">
				<label for="inputCategory_id">商品カテゴリー</label>
				<select class="form-select wide-input" id="inputCategory_id">
					<option value="" disabled selected hidden>選択してください</option>
					<option value="1">食品</option>
					<option value="2">消耗品</option>
					<option value="3">文房具</option>
					<option value="4">衣類</option>
					<option value="5">食器</option>
				</select>
			</div>
			<div class="form-group">
				<label for="inputTrade_name">商品名 <span class="badge bg-secondary">部分一致</span> </label>
				<input type="text" class="form-control wide-input" id="inputTrade_name" name="trade_name" placeholder="商品名">
			</div>
			<div class="form-group note-group">
				<label for="inputNote" class="pt-10">備考 <span class="badge bg-secondary">部分一致</span> </label>
				<input type="text" class="form-control wide-input" id="inputNote" name="note" placeholder="備考">
			</div>
			<div class="form-group d-flex" style="margin-left: 210px;">
				<button type="submit" class="btn btn-primary me-2">検索</button>
				<button type="reset" class="btn btn-outline-secondary">クリア</button>
			</div>
		</form>
	</main>
</body>
</html>