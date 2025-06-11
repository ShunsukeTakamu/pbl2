<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント登録</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css">
<style>
	body {
		display: flex;
    	flex-direction: column;
		align-items: center;
	}
	 h2 {
        margin: 40px 0 30px;
        text-align: center;
    }
    .form-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
    }
	.form-group {
        display: flex;
        align-items: center;
        margin-bottom: 15px;
    }
    .form-group > label {
    	width: 200px; 
   		font-weight: bold;
   		display: flex;
    	justify-content: flex-end;
    	margin-right: 10px;
    	text-align: right;
	}
	.form-group .input-field {
    	flex: 1;
	}
    .required {
        display: inline-block;
        background-color: #666;
        color: #fff;
        font-size: 12px;
        padding: 2px 6px;
        border-radius: 12px;
        margin-left: 10px;
        white-space: nowrap;
    }
    header, .navbar {
    	width: 100%;
   		box-sizing: border-box;
	}
	.nav-links {
		display: flex;
	}
	.nav-links li.logout-item {
		margin-left: auto;
	}
    .roles {
        display: flex;
        align-items: center;
        gap: 10px; 
    }
    .roles label {
        margin-right: 6px;
    }
    .submit-btn {
        text-align: center;
        margin-top: 20px;
    }
    .submit-btn button {
        padding: 8px 20px;
        font-size: 14px;
        background-color: #337ab7;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .submit-btn button:hover {
       background-color: #286090;
    }
</style>
</head>
<body class="register-page">
<header>
		<nav class="navbar">
			<div class="logo">物品売上管理システム</div>
			<ul class="nav-links">
				<li><a class="active" href="C0020.jsp">ダッシュボード</a></li>
				<li><a href="S0010.jsp">売上登録</a></li>
				<li><a href="S0020.jsp">売上検索</a></li>
				<li><a href="S0030.jsp">アカウント登録</a></li>
				<li><a href="S0040.jsp">アカウント検索</a></li>
				<li class="logout-item"><a href="logout.jsp">ログアウト</a></li>
			</ul>
		</nav>
</header>

	<h2>アカウント登録</h2>
	<div class="form-container">
	<form action="S0030" method="post">
		<div class="form-group">
			<label>氏名 <span class="required">必須</span></label>
			<input type="text" name="name" placeholder="氏名" required>
		</div>
		<div class="form-group">
			<label>メールアドレス <span class="required">必須</span></label>
        	<input type="email" name="email" placeholder="メールアドレス" required>
		</div>
		<div class="form-group">
			 <label>パスワード <span class="required">必須</span></label>
        	<input type="password" name="password" placeholder="パスワード" required>
		</div>
		<div class="form-group">
			<label>パスワード（確認）<span class="required">必須</span></label>
        	<input type="password" name="confirmPassword" placeholder="パスワード（確認）" required>
		</div>
		<div class="form-group">
			<label>権限 <span class="required">必須</span></label>
			<div class="roles">
        	<label><input type="radio" name="role" value="none" required> 権限なし</label>
	        <label><input type="radio" name="role" value="view"> 参照</label>
	        <label><input type="radio" name="role" value="update"> 更新</label>
		</div>
		</div>
		<div class="submit-btn">
        	<button type="submit">✔ 登録</button>
    	</div>
	</form>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>