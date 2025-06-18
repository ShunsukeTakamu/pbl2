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
        background-color: #e9ecef;
        border-radius: 4px;
        border: 1px solid #ced4da;
        min-width: 200px;
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
    .submit-btn button.ok-btn {
        padding: 8px 20px;
        font-size: 14px;
        background-color: #337ab7;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-weight: bold;
        margin-right: 10px;
    }
    .submit-btn button.ok-btn:hover {
       background-color: #286090;
    }
    .cancel-btn {
    	background-color: #f5f5f5;   
    	color: #000;
    	border: none;
        padding: 8px 20px;
        border-radius: 4px;
        cursor: pointer;             
	}
	.cancel-btn:hover {
    	background-color: #bbb;   
	}
	.required {
        background-color: #666;
        color: #fff;
        font-size: 12px;
        padding: 2px 6px;
        border-radius: 12px;
        margin-left: 10px;
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
<body>
<%
	String name = (String) session.getAttribute("name");
	String mail = (String) session.getAttribute("mail");
	String password = (String) session.getAttribute("password");
	String role = (String) session.getAttribute("role");
%>
	<header>
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>
	 <h2>アカウントを登録してよろしいですか？</h2>
	 <% String error = (String) request.getAttribute("error"); %>
	<% if (error != null) { %>
    <div style="color: red; text-align: center; font-weight: bold; margin-bottom: 20px;">
        <%= error %>
    </div>
	<% } %>
    <div class="form-container">
        <form action="S0031.html" method="post">
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
			<div class="input-field"><%= name %></div>
			<input type="hidden" name="name" value="<%= name %>">
		</div>
		<div class="form-group">
			<label>メールアドレス <span class="required">必須</span></label>
        	<div class="input-field"><%= mail %></div>
            <input type="hidden" name="mail" value="<%= mail %>">
		</div>
		<div class="form-group">
			 <label>パスワード <span class="required">必須</span></label>
        	<div class="input-field">********</div>
            <input type="hidden" name="password" value="<%= password %>">
		</div>
		<div class="form-group">
			<label>パスワード（確認）<span class="required">必須</span></label>
        	<div class="input-field">********</div>
            <input type="hidden" name="password" value="<%= password %>">
		</div>
		<div class="form-group">
			<label>権限 <span class="required">必須</span></label>
			<div class="roles">
        	<label><input type="radio" name="role" value="none" <%= "none".equals(role) ? "checked" : "" %> disabled> 権限なし</label>
	        <label><input type="radio" name="role" value="view" <%= "view".equals(role) ? "checked" : "" %> disabled> 参照</label>
	        <label><input type="radio" name="role" value="update" <%= "update".equals(role) ? "checked" : "" %> disabled> 更新</label>
		</div>
			<input type="hidden" name="role" value="<%= role %>">	
		</div>
		<div class="submit-btn">
        	<button type="submit" class="ok-btn">O K</button>
        	<button type="button" class="cancel-btn" onclick="location.href='S0030.jsp?reset=true'">キャンセル</button>
    	</div>
	</form>
	</div>
	
</body>
</html>