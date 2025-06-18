<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    .alert ul {
		margin-bottom: 0;
		padding-left: 1.2rem;
	}
	.alert li {
		margin-bottom: 0;
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
	<header>
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>

	<h2>アカウント登録</h2>
	<div class="form-container">
	
	<form action="S0030.html" method="post">
	<c:if test="${not empty errorMsg}">
    <div class="alert alert-danger" style="margin-bottom: 1em;">
        <ul>
            <c:forEach var="msg" items="${errorMsg}">
                <li>${msg}</li>
            </c:forEach>
        </ul>
    </div>
	</c:if>
	
		<div class="form-group">
			<label>氏名 <span class="required">必須</span></label>
			<input type="text" name="name" class="input-field" placeholder="氏名" value="${fn:escapeXml(name)}">
		</div>
		<div class="form-group">
			<label>メールアドレス <span class="required">必須</span></label>
        	<input type="mail" name="mail" class="input-field" placeholder="メールアドレス" value="${fn:escapeXml(mail)}">
		</div>
		<div class="form-group">
			 <label>パスワード <span class="required">必須</span></label>
        	<input type="password" name="password" class="input-field" placeholder="パスワード" value="${fn:escapeXml(password)}">
		</div>
		<div class="form-group">
			<label>パスワード（確認）<span class="required">必須</span></label>
        	<input type="password" name="confirmPassword" class="input-field" placeholder="パスワード（確認）" value="${fn:escapeXml(confirmPassword)}">
		</div>
		<div class="form-group">
			<label>権限 <span class="required">必須</span></label>
			<div class="roles">
        	<label><input type="radio" name="role" value="none" <c:if test="${empty role or role == 'none'}">checked</c:if>> 権限なし</label>
	        <label><input type="radio" name="role" value="view" <c:if test="${role == 'view'}">checked</c:if>> 参照</label>
	        <label><input type="radio" name="role" value="update" <c:if test="${role == 'update'}">checked</c:if>> 更新</label>
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