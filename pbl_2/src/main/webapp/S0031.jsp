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
<script src="js/bootstrap.bundle.min.js"></script>
<style>
	
     form {
    max-width: 600px;
    margin: 0 auto;
  	}
  	.form-label span.badge {
    margin-left: 0.5em;
  	}
  	.alert ul {
		margin-bottom: 0;
		padding-left: 1.2rem;
	}
	.alert li {
		margin-bottom: 0;
	}
  	@media (max-width: 768px) {
       .col-md-3, .col-md-9 {
      width: 100% !important;
    }
    .col-md-3 {
      text-align: left !important;
      margin-bottom: 0.25rem;
    }
  }	
</style>
</head>
<body>
<%
	//セッションから入力内容を取得（POST前の一時保存用）
	String name = (String) session.getAttribute("name");
	String mail = (String) session.getAttribute("mail");
	String password = (String) session.getAttribute("password");
	String[] authorities = (String[]) session.getAttribute("authorities");
%>
	<header>
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>
	
	<main class="container mt-5">
	 <h1>アカウントを登録してよろしいですか？</h1>
	 <% String error = (String) request.getAttribute("error"); %>
	<% if (error != null) { %>
    <div class="alert alert-danger text-center fw-bold mb-4">
        <%= error %>
    </div>
	<% } %>
        <form action="S0031.html" method="post">
        <c:if test="${not empty errorMsg}">
    	<div class="alert alert-danger mb-3">
      		<ul>
        		<c:forEach var="msg" items="${errorMsg}">
          			<li>${msg}</li>
        		</c:forEach>
     		 </ul>
   		</div>
  		</c:if>
		<div class="row align-items-center mb-3">
		<label class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center">
			氏名 <span class="badge bg-secondary">必須</span></label>
			<div class="col-12 col-md-9">
			<div class="form-control bg-light"><%= name %></div>
        		<input type="hidden" name="name" value="<%= name %>">
      		</div>
    	</div>
		<div class="row align-items-center mb-3">
		<label class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center" style="white-space: nowrap;">
			メールアドレス <span class="badge bg-secondary">必須</span></label>
        	<div class="col-12 col-md-9">
        	<div class="form-control bg-light"><%= mail %></div>
        		<input type="hidden" name="mail" value="<%= mail %>">
      		</div>
    	</div>
		<div class="row align-items-center mb-3">
			<label class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center">
        	パスワード <span class="badge bg-secondary">必須</span></label>
      		<div class="col-12 col-md-9">
       		<div class="form-control bg-light">********</div>
        		<input type="hidden" name="password" value="<%= password %>">
      		</div>
    	</div>
		<div class="row align-items-center mb-3">
			<label class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center">
        	パスワード（確認） <span class="badge bg-secondary">必須</span></label>
      		<div class="col-12 col-md-9">
        	<div class="form-control bg-light">********</div>
        		<input type="hidden" name="password" value="<%= password %>">
      		</div>
    	</div>
		<div class="row align-items-center mb-3">
			<label class="col-12 col-md-3 col-form-label text-start text-md-end d-flex justify-content-md-end align-items-center">
        	権限 <span class="badge bg-secondary">必須</span></label>
      		<div class="col-12 col-md-9">
        	<div class="form-check">
          		<input class="form-check-input" type="checkbox" disabled <%= java.util.Arrays.asList(authorities).contains("0") ? "checked" : "" %>>
          		<label class="form-check-label">権限なし</label>
          		<input type="hidden" name="authorities" value="<%= java.util.Arrays.asList(authorities).contains("0") ? "0" : "" %>">
        	</div>
        	<div class="form-check">
          		<input class="form-check-input" type="checkbox" disabled <%= java.util.Arrays.asList(authorities).contains("1") ? "checked" : "" %>>
          		<label class="form-check-label">売上登録</label>
         		<input type="hidden" name="authorities" value="<%= java.util.Arrays.asList(authorities).contains("1") ? "1" : "" %>">
        	</div>
        	<div class="form-check">
          		<input class="form-check-input" type="checkbox" disabled <%= java.util.Arrays.asList(authorities).contains("2") ? "checked" : "" %>>
          		<label class="form-check-label">アカウント登録</label>
          		<input type="hidden" name="authorities" value="<%= java.util.Arrays.asList(authorities).contains("2") ? "2" : "" %>">
        	</div>
      		</div>
    	</div>
    	<div class="row">

		<div class="col-12 text-center">
        	<button type="submit" class="btn btn-primary me-2">OK</button>
        	<button type="button" class="btn btn-outline-secondary" onclick="location.href='S0030.jsp?reset=true'">キャンセル</button>
      	</div>
    	</div>
	</form>
	</main>
</body>
</html>