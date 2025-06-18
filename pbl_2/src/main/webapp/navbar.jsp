<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
  String uri = request.getRequestURI();
  request.setAttribute("uri", uri);
%>

<nav class="navbar">
  <div class="logo">物品売上管理システム</div>
  <ul class="nav-links">
    <li><a class="${ fn:endsWith(uri, 'C0020.jsp') ? 'active' : '' }" href="C0020.html">ダッシュボード</a></li>

    <c:if test="${ not empty account and (account.authority == \"b'1'\" or account.authority == \"b'11'\") }">
      <li><a class="${ fn:endsWith(uri, 'S0010.jsp') ? 'active' : '' }" href="S0010.html">売上登録</a></li>
    </c:if>

    <li><a class="${ fn:endsWith(uri, 'S0020.jsp') ? 'active' : '' }" href="S0020.html">売上検索</a></li>

    <c:if test="${ not empty account and (account.authority == \"b'10'\" or account.authority == \"b'11'\") }">
      <li><a class="${ fn:endsWith(uri, 'S0030.jsp') ? 'active' : '' }" href="S0030.html">アカウント登録</a></li>
    </c:if>

    <li><a class="${fn:endsWith(uri, 'C0040.jsp') ? 'active' : ''}" href="C0040.html">アカウント検索</a></li>

    <li class="logout">
      <a href="logout.jsp">ログアウト</a>
    </li>
  </ul>
</nav>