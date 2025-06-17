<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>売上管理</title>
<link
	href="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.css"
	rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<script src="js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>
	<header>
		<!-- ナビバーのインクルード -->
    	<jsp:include page="/navbar.jsp" />
	</header>
	<main>
		<div class="container mt-4">
			<div class="row mb-3">
				<div class="col-12">
					<h2 class="fw-bold">売上管理ダッシュボード</h2>
				</div>
			</div>

			<div class="row g-4">
				<!-- 今年の売上 -->
				<div class="col-lg-3 col-md-6 col-sm-12">
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
					String arrowIcon = isIncrease ? "↑" : "↓";
					String arrowColor = isIncrease ? "text-success" : "text-danger";
					%>
					<div class="card shadow-sm border-0 rounded-3">
						<div class="card-body">
							<h6 class="text-muted"><%=LocalDate.now().getYear()%>年の売上
							</h6>
							<h3 class="fw-bold text-primary"><%=String.format("%,d円", totalSales)%></h3>
							<p class="mb-0 <%=arrowColor%>"><%=arrowIcon%>
								<%=String.format("%.1f%%", changeRate)%></p>
						</div>
						<div class="card-footer bg-transparent border-0 pt-0">
							<canvas id="flush-area-chart-yellow" height="15"></canvas>
						</div>
					</div>
				</div>

				<!-- 総売上 -->
				<div class="col-lg-3 col-md-6 col-sm-12">
					<%
					Integer allTimeSales = (Integer) request.getAttribute("allTimeSales");
					if (allTimeSales == null)
						allTimeSales = 0;
					%>
					<div class="card shadow-sm border-0 rounded-3">
						<div class="card-body">
							<h6 class="text-muted">総売上（全期間）</h6>
							<h3 class="fw-bold text-primary"><%=String.format("%,d円", allTimeSales)%></h3>
						</div>
						<div class="card-footer bg-transparent border-0 pt-0">
							<canvas id="flush-area-chart-yellow" height="40"></canvas>
						</div>
					</div>
				</div>


				<!-- 登録者数 -->
				<div class="col-lg-3 col-md-6 col-sm-12">
					<div class="card shadow-sm border-0 rounded-3">
						<div class="card-body">
							<h6 class="text-muted">登録者数</h6>
							<h3 class="fw-bold text-primary"><%=request.getAttribute("accountCount")%>人
							</h3>
						</div>
						<div class="card-footer bg-transparent border-0 pt-0">
							<canvas id="flush-area-chart-yellow" height="40"></canvas>
						</div>
					</div>
				</div>

				<!-- 総売上（推移） -->
				<div class="col-12">
					<div class="card shadow-sm border-0 rounded-3">
						<div class="card-body">
							<h6 class="text-muted">総売上（推移）</h6>
							<canvas id="main-toggle-line-chart" height="100"></canvas>
						</div>
					</div>
				</div>

				<!-- 担当者売上ランキング -->
				<div class="col-md-6">
					<div class="card shadow-sm border-0 rounded-3">
						<div class="card-body">
							<h6 class="text-muted">担当者売上ランキング</h6>
							<canvas id="salesChart" height="200"></canvas>
						</div>
					</div>
				</div>

				<!-- カテゴリー別売上 -->
				<div class="col-md-6">
					<div class="card shadow-sm border-0 rounded-3">
						<div class="card-body">
							<h6 class="text-muted">カテゴリー別売上（円）</h6>
							<canvas id="categorySalesChart" height="200"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>

	<!-- Chart.js scripts remain unchanged -->
	<!-- Chart.js config will be injected by JSP scriptlet as in original -->


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

// 年別売上グラフのデータ作成
const yearLabels = [
  <%if (saleDates != null) {
	for (int i = 0; i < saleDates.size(); i++) {%>
    "<%=saleDates.get(i)%>"<%=(i < saleDates.size() - 1) ? "," : ""%>
  <%}
}%>
];

const yearlyData = [
  <%if (dailyTotals != null) {
	for (int i = 0; i < dailyTotals.size(); i++) {%>
    <%=dailyTotals.get(i)%><%=(i < dailyTotals.size() - 1) ? "," : ""%>
  <%}
}%>
];

const ctxLine = document.getElementById('main-toggle-line-chart').getContext('2d');
new Chart(ctxLine, {
  type: 'line',
  data: {
    labels: yearLabels,
    datasets: [{
      label: '年別売上額（円）',
      data: yearlyData,
      fill: true,
      borderColor: 'rgba(54, 162, 235, 1)',
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      tension: 0.4
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


	<!-- Initialization script -->
	<!--	<script-->
	<!--		src="//themes.materializecss.com/cdn/shop/t/1/assets/dashboard.min.js?v=4816808830627109061528498722"></script>-->

</body>
</html>