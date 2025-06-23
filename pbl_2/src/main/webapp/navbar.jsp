<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
  String uri = request.getRequestURI();
  request.setAttribute("uri", uri);

  Integer auth = (Integer) session.getAttribute("loginAuthority");
  boolean hasSales = auth != null && (auth & 1) != 0;
  boolean hasAccount = auth != null && (auth & 2) != 0;

  request.setAttribute("hasSales", hasSales);
  request.setAttribute("hasAccount", hasAccount);
%>
<style>
.navbar-nav .nav-link {
  padding-left: 1rem;
  padding-right: 1rem;
}
.nav-link.active {
  background-color: #e9ecef;
  font-weight: bold;
  margin-top:5px;
  border-radius: 0.375rem;
}

</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
  <div class="container-fluid">
    <a class="navbar-brand fw-bold" href="#">物品売上管理システム</a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
      aria-controls="navbarNav" aria-expanded="false" aria-label="メニュー切替">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link ${fn:endsWith(uri, 'C0020.jsp') ? 'active' : ''}" href="C0020.html">ダッシュボード</a>
        </li>
        <c:if test="${hasSales}">
          <li class="nav-item">
            <a class="nav-link ${fn:endsWith(uri, 'S0010.jsp') ? 'active' : ''}" href="S0010.html">売上登録</a>
          </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link ${fn:endsWith(uri, 'S0020.jsp') ? 'active' : ''}" href="S0020.html">売上検索</a>
        </li>
        <c:if test="${hasAccount}">
          <li class="nav-item">
            <a class="nav-link ${fn:endsWith(uri, 'S0030.jsp') ? 'active' : ''}" href="S0030.html">アカウント登録</a>
          </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link ${fn:endsWith(uri, 'S0040.jsp') ? 'active' : ''}" href="S0040.html">アカウント検索</a>
        </li>
      </ul>

      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link text-danger" href="logout">ログアウト</a>
        </li>
      </ul>

    </div>
  </div>
</nav>
