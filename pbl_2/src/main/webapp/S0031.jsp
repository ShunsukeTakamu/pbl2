<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント登録確認</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css">
<style>
    .form-container { margin: 50px auto; width: 600px; }
    .form-group { margin-bottom: 15px; display: flex; }
    .form-group label { width: 150px; font-weight: bold; }
    .form-group .value { flex: 1; }
    .button-group { text-align: center; margin-top: 30px; }
    .button-group button { margin: 0 10px; padding: 8px 20px; }
    .error-message { color: red; text-align: center; margin-top: 20px; }
</style>
</head>
<body>
<header>
		<nav class="navbar">
			<div class="logo">物品売上管理システム</div>
			<ul class="nav-links">
				<li><a class="active" href="C0020.jsp">ダッシュボード</a></li>
				<li><a href="S0010.jsp">売上登録</a></li>
				<li><a href="S0020.jsp">売上検索</a></li>
				<li><a href="S0030.jsp">アカウント登録</a></li>
				<li><a href="S0040.jsp">アカウント検索</a></li>
				<li><a href="logout.jsp" class="logout" class="right">ログアウト</a></li>
			</ul>
		</nav>
	</header>
	 <h2 style="text-align:center;">アカウントを登録してよろしいですか？</h2>
    <div class="form-container">
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
        <form action="S0031" method="post">
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
		<div class="roles">
			<label>権限 <span class="required">必須</span></label>
        	<label><input type="radio" name="role" value="none" required> 権限なし</label>
	        <label><input type="radio" name="role" value="view"> 参照</label>
	        <label><input type="radio" name="role" value="update"> 更新</label>
		</div>
		<div class="submit-btn">
        	<button type="submit">✔ 登録</button>
        	<button type="submit">キャンセル</button>
    	</div>
	</form>
	</div>
</body>
</html>