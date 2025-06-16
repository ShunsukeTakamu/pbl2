<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>


<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description" content="">
<title>売上管理</title>
<link
	href="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.css"
	rel="stylesheet">
<!--<link-->
<!--	href="//themes.materializecss.com/cdn/shop/t/1/assets/jqvmap.css?v=162757563705857184351528499283"-->
<!--	rel="stylesheet">-->
<!--<link-->
<!--	href="//themes.materializecss.com/cdn/shop/t/1/assets/flag-icon.min.css?v=107574258948483483761528499307"-->
<!--	rel="stylesheet">-->
<!-- Fullcalendar-->
<!--<link-->
<!--	href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.7.0/fullcalendar.min.css"-->
<!--	rel="stylesheet">-->
<!-- Materialize-->
<link
	href="//themes.materializecss.com/cdn/shop/t/1/assets/admin-materialize.min.css?v=88505356707424191531528497992"
	rel="stylesheet">
<!-- Material Icons-->
<!--<link href="https://fonts.googleapis.com/icon?family=Material+Icons"-->
<!--	rel="stylesheet">-->

<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<script src="js/bootstrap.bundle.min.js"></script>

</head>


<script src="js/bootstrap.bundle.min.js"></script>
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
<body>

	<main>
		<div class="container">
			<div class="masonry row">
				<div class="col s12">
					<h2>Dashboard</h2>
				</div>
				<div class="col l3 m6 s12">

					<%
					Integer totalSales = (Integer) request.getAttribute("totalSales");
					Double changeRate = (Double) request.getAttribute("changeRate");
					Boolean isIncrease = (Boolean) request.getAttribute("isIncrease");

					if (totalSales == null)
						totalSales = 0;
					if (changeRate == null)
						changeRate = 0.0;
					if (isIncrease == null)
						isIncrease = true;

					String changeClass = isIncrease ? "increase" : "decrease";
					String arrowIcon = isIncrease ? "↑" : "↓";
					%>

					<div class="card">
						<div class="card-stacked">
							<div class="card-metrics card-metrics-static">
								<div class="card-metric">
									<div class="card-metric-title">総売上</div>
									<div class="card-metric-value">
										<%=String.format("%,d円", totalSales)%>
									</div>
									<div class="card-metric-change <%=changeClass%>">
										<i class="material-icons"><%=arrowIcon%></i>
										<%=String.format("%.1f%%", changeRate)%>
									</div>
								</div>
							</div>
						</div>
						<div class="card-chart">
							<canvas id="flush-area-chart-blue" height="100px"></canvas>
						</div>
					</div>


				</div>
				<div class="col l3 m6 s12">

					<div class="card">
						<div class="card-stacked">
							<div class="card-metrics card-metrics-static">
								<div class="card-metric">
									<div class="card-metric-title">アクセス数</div>
									<div class="card-metric-value">11回</div>
									<div class="card-metric-change increase">
										<i class="material-icons left">keyboard_arrow_up</i> 8%
									</div>
								</div>
							</div>
						</div>
						<div class="card-chart">
							<canvas id="flush-area-chart-yellow" height="100px"></canvas>
						</div>
					</div>

				</div>
				<div class="col l3 m6 s12">

					<div class="card">
						<div class="card-stacked">
							<div class="card-metrics card-metrics-static">
								<div class="card-metric">
									<div class="card-metric-title">登録者数</div>
									<div class="card-metric-value">23人</div>
									<div class="card-metric-change decrease">
										<i class="material-icons left">keyboard_arrow_down</i> 2%
									</div>
								</div>
							</div>
						</div>
						<div class="card-chart">
							<canvas id="flush-area-chart-pink" height="100"></canvas>
						</div>
					</div>

				</div>
				<!--				<div class="col l3 m6 s12">-->

				<!--					<div class="card">-->
				<!--						<div class="card-stacked">-->
				<!--							<div class="card-metrics card-metrics-static">-->
				<!--								<div class="card-metric">-->
				<!--									<div class="card-metric-title">Conversion Rate</div>-->
				<!--									<div class="card-metric-value">0.24%</div>-->
				<!--									<div class="card-metric-change decrease">-->
				<!--										<i class="material-icons left">keyboard_arrow_down</i> 9%-->
				<!--									</div>-->
				<!--								</div>-->
				<!--							</div>-->
				<!--						</div>-->
				<!--						<div class="card-chart">-->
				<!--							<canvas id="flush-area-chart-green" height="100"></canvas>-->
				<!--						</div>-->
				<!--					</div>-->

				<!--				</div>-->

				<div class="col s12">
					<div class="card">
						<div
							class="card-metrics card-metrics-toggle card-metrics-centered">
							<div class="card-metric waves-effect active"
								data-metric="revenue">
								<div class="card-metric-title">総売上</div>
								<div class="card-metric-value">
									<%=String.format("%,d円", (Integer) request.getAttribute("totalSales"))%>
								</div>
							</div>
						</div>
						<div class="card-content">
							<canvas class="card-chart" id="main-toggle-line-chart"
								width="1000" height="300"></canvas>
						</div>
					</div>
				</div>

				<!--担当者の売上ランキング-->
				<div class="col m6 s12">
					<div class="card">
						<div class="card-content">
							<div class="card-title">担当者売上ランキング</div>
							<div class="chart-wrapper">
								<canvas id="salesChart" width="400" height="400"></canvas>
							</div>
						</div>
					</div>
				</div>

				<!--カテゴリー別売上-->
				<div class="col m6 s12">
					<div class="card">
						<div class="card-content">
							<span class="card-title">カテゴリー別売上（円）</span>
							<canvas id="categorySalesChart" width="1000" height="400"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>


	<!--新規追加 担当者売上ランキング-->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<%
	List<String> saleDates = (List<String>) request.getAttribute("saleYears");
	List<Integer> dailyTotals = (List<Integer>) request.getAttribute("yearlyTotals");

	List<String> names = (List<String>) request.getAttribute("accountNames");
	List<Integer> totals = (List<Integer>) request.getAttribute("salesTotals");

	List<String> categoryNames = (List<String>) request.getAttribute("categoryNames");
	List<Integer> categoryTotals = (List<Integer>) request.getAttribute("categoryTotals");
	%>
	<script>

// -----------------------------
// カテゴリー別売上グラフのデータ作成
// -----------------------------

// カテゴリー名のラベル（例：食品、日用品...）
const categoryLabels = [
  <%if (categoryNames != null) {
	for (int i = 0; i < categoryNames.size(); i++) {%>
    "<%=categoryNames.get(i)%>"<%=(i < categoryNames.size() - 1) ? "," : ""%>
  <%}
}%>
];

// カテゴリーごとの売上合計（円）
const categoryData = [
  <%if (categoryTotals != null) {
	for (int i = 0; i < categoryTotals.size(); i++) {%>
    <%=categoryTotals.get(i)%><%=(i < categoryTotals.size() - 1) ? "," : ""%>
  <%}
}%>
];

// カテゴリー売上グラフ描画
const ctxCategory = document.getElementById('categorySalesChart').getContext('2d');
new Chart(ctxCategory, {
  type: 'bar',
  data: {
    labels: categoryLabels,
    datasets: [{
      label: 'カテゴリー別売上（円）',
      data: categoryData,
      backgroundColor: 'rgba(255, 159, 64, 0.5)',
      borderColor: 'rgba(255, 159, 64, 1)',
      borderWidth: 1
    }]
  },
  options: {
    responsive: true,
    scales: {
      y: {
        beginAtZero: true,
        ticks: {
          callback: function(value) {
            return '¥' + value.toLocaleString(); // カンマ付き円表示
          }
        }
      }
    }
  }
});

// -----------------------------
// 日別売上グラフのデータ作成
// -----------------------------

// 売上日（X軸のラベル：日付）
const dateLabels = [
  <%if (saleDates != null) {
	for (int i = 0; i < saleDates.size(); i++) {%>
    "<%=saleDates.get(i)%>"<%=(i < saleDates.size() - 1) ? "," : ""%>
  <%}
}%>
];

// 日別の売上金額（Y軸の値）
const dailyData = [
  <%if (dailyTotals != null) {
	for (int i = 0; i < dailyTotals.size(); i++) {%>
    <%=dailyTotals.get(i)%><%=(i < dailyTotals.size() - 1) ? "," : ""%>
  <%}
}%>
];

// 日別売上のラインチャート描画
const ctxLine = document.getElementById('main-toggle-line-chart').getContext('2d');
new Chart(ctxLine, {
  type: 'line',
  data: {
    labels: dateLabels,
    datasets: [{
      label: '日別売上額（円）',
      data: dailyData,
      fill: true,
      borderColor: 'rgba(54, 162, 235, 1)',
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      tension: 0.4  // 線のなめらかさ
    }]
  },
  options: {
    responsive: true,
    scales: {
      y: {
        beginAtZero: true,
        ticks: {
          callback: function(value) {
            return '¥' + value.toLocaleString(); // カンマ付き円表示
          }
        }
      }
    }
  }
});

// -----------------------------
// 担当者別売上ランキンググラフ
// -----------------------------

// 担当者名のラベル
const labels = [
  <%if (names != null) {
	for (int i = 0; i < names.size(); i++) {
		String name = names.get(i);%>
    "<%=name%>"<%=(i < names.size() - 1) ? "," : ""%>
  <%}
}%>
];

// 担当者ごとの売上金額
const data = [
  <%if (totals != null) {
	for (int i = 0; i < totals.size(); i++) {
		int val = totals.get(i);%>
    <%=val%><%=(i < totals.size() - 1) ? "," : ""%>
  <%}
}%>
];

// 担当者売上の棒グラフ描画
const ctx = document.getElementById('salesChart').getContext('2d');
new Chart(ctx, {
  type: 'bar',
  data: {
    labels: labels,
    datasets: [{
      label: '売上合計（円）',
      data: data,
      backgroundColor: 'rgba(75, 192, 192, 0.5)',
      borderColor: 'rgba(75, 192, 192, 1)',
      borderWidth: 1
    }]
  },
  options: {
    responsive: true,
    scales: {
      y: {
        beginAtZero: true,
        ticks: {
          callback: function(value) {
            return '¥' + value.toLocaleString();
          }
        }
      }
    }
  }
});
</script>


	<!-- Scripts -->


	<!-- External libraries -->



	<!-- Initialization script -->
	<script
		src="//themes.materializecss.com/cdn/shop/t/1/assets/dashboard.min.js?v=4816808830627109061528498722"></script>

</body>
</html>