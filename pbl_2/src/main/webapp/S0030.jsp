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
	 h2 {
        margin: 40px 0 30px;
        text-align: center;
    }
    .form-container {
       max-width: 600px;
        margin: 50px auto;
        padding: 0 20px;
    }
	.form-group {
        display: flex;
        align-items: center;
        margin-bottom: 1.5rem;
    }
    .form-group > label {
    	width: 200px;
    	text-align: right;
    	display: flex;
    	align-items: center;
    	justify-content: flex-end;
    	margin-right: 15px;
    	gap: 6px;
	}
	.input-field {
    	flex: 1;
    	padding: 8px;
        font-size: 14px;
        border-radius: 4px;
        border: 1px solid #ced4da;
        min-width: 200px;
	}
    .required {
         background-color: #666;
        color: #fff;
        font-size: 12px;
        padding: 2px 6px;
        border-radius: 12px;
        margin-left: 10px;
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
    @media screen and (max-width: 768px) {
        h2 {
            text-align: center;
        }

        .form-group {
            flex-direction: column;
            align-items: stretch;
        }

        .form-group > label {
            width: 100%;
            text-align: left;
            justify-content: flex-start;
            margin-bottom: 5px;
        }

        .input-field {
            width: 100%;
        }

        .roles {
            flex-direction: column;
            align-items: flex-start;
        }
    }
</style>
</head>
<body class="register-page">
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
				<li class="logout"><a href="logout.jsp">ログアウト</a></li>
			</ul>
		</nav>
</header>

	<h2>アカウント登録</h2>
	<div class="form-container">
	<form action="S0030.html" method="post">
		<div class="form-group">
			<label>氏名 <span class="required">必須</span></label>
			<input type="text" name="name" class="input-field" placeholder="氏名" required>
		</div>
		<div class="form-group">
			<label>メールアドレス <span class="required">必須</span></label>
        	<input type="mail" name="mail" class="input-field" placeholder="メールアドレス" required>
		</div>
		<div class="form-group">
			 <label>パスワード <span class="required">必須</span></label>
        	<input type="password" name="password" class="input-field" placeholder="パスワード" required>
		</div>
		<div class="form-group">
			<label>パスワード（確認）<span class="required">必須</span></label>
        	<input type="password" name="confirmPassword" class="input-field" placeholder="パスワード（確認）" required>
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