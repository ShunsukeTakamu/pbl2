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
<link
	href="//themes.materializecss.com/cdn/shop/t/1/assets/jqvmap.css?v=162757563705857184351528499283"
	rel="stylesheet">
<link
	href="//themes.materializecss.com/cdn/shop/t/1/assets/flag-icon.min.css?v=107574258948483483761528499307"
	rel="stylesheet">
<!-- Fullcalendar-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.7.0/fullcalendar.min.css"
	rel="stylesheet">
<!-- Materialize-->
<link
	href="//themes.materializecss.com/cdn/shop/t/1/assets/admin-materialize.min.css?v=88505356707424191531528497992"
	rel="stylesheet">
<!-- Material Icons-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
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
					<h2>売上管理だっしゅぼーど</h2>
				</div>
				<div class="col l3 m6 s12">

					<div class="card">
						<div class="card-stacked">
							<div class="card-metrics card-metrics-static">
								<div class="card-metric">
									<div class="card-metric-title">総売上</div>
									<div class="card-metric-value">1,232,343,234円</div>
									<div class="card-metric-change increase">
										<i class="material-icons left">keyboard_arrow_up</i> 12%
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
								<div class="card-metric-value">$12,476.00</div>
								<div class="card-metric-change">
									<i class="material-icons">keyboard_arrow_up</i> 12%
								</div>
							</div>
							<div class="card-metric waves-effect" data-metric="users">
								<div class="card-metric-title">利用者数</div>
								<div class="card-metric-value">2024</div>
								<div class="card-metric-change">
									<i class="material-icons">keyboard_arrow_up</i> 9%
								</div>
							</div>
							<div class="card-metric waves-effect" data-metric="ctr">
								<div class="card-metric-title">満足度</div>
								<div class="card-metric-value">0.20%</div>
								<div class="card-metric-change">
									<i class="material-icons">keyboard_arrow_up</i> 4%
								</div>
							</div>
						</div>
						<div class="card-content">
							<canvas class="card-chart" id="main-toggle-line-chart"
								width="400" height="400"></canvas>
						</div>
					</div>
				</div>

				<!--更新履歴-->
				<div class="col m6 s12">
					<div class="card">
						<div class="card-content">
							<span class="card-title">更新履歴</span>
							<ul class="badge-updates">
								<li><span class="new badge red" data-badge-caption="refund"></span>
									<span class="message">45$ refunded to Alan Chang</span> <span
									class="time">14 minutes ago</span></li>
								<li><span class="new badge green"
									data-badge-caption="purchase"></span> <span class="message">45$
										from Alan Chang</span> <span class="time">14 minutes ago</span></li>
								<li><span class="new badge red" data-badge-caption="refund"></span>
									<span class="message">45$ refunded to Alan Chang</span> <span
									class="time">14 minutes ago</span></li>
							</ul>
						</div>
					</div>
				</div>


				<!--期間別カテゴリー売上推移-->
				<div class="col m6 s12">
					<div id="tab-legend-chart-card" class="card primary-color">
						<div class="card-content">
							<p class="white-text">
								こちらは期間別で売上の推移を確認できます。<br> 期間を選択してください。
							</p>
						</div>
						<div class="card-content">
							<canvas class="card-chart" id="tab-legend-line-chart" width="400"
								height="400"></canvas>
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
							<span class="card-title">カテゴリー別</span>
							<div class="row row-vertical-center">
								<div class="col s6">
									<canvas id="doughnut-chart" width="50%"></canvas>
								</div>
								<div class="col s6">
									<div class="chart-legend-wrapper"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!--新規追加 担当者売上ランキング-->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<%
	List<String> names = (List<String>) request.getAttribute("accountNames");
	List<Integer> totals = (List<Integer>) request.getAttribute("salesTotals");
	%>
	<script>
const labels = [
  <%if (names != null) {
	for (int i = 0; i < names.size(); i++) {
		String name = names.get(i);%>
    "<%=name%>"<%=(i < names.size() - 1) ? "," : ""%>
  <%}
}%>
];

const data = [
  <%if (totals != null) {
	for (int i = 0; i < totals.size(); i++) {
		int val = totals.get(i);%>
    <%=val%><%=(i < totals.size() - 1) ? "," : ""%>
  <%}
}%>
];

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
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.2/moment.min.js"></script>

	<!-- External libraries -->
	
	<!-- ChartJS -->
	<!--	<script type="text/javascript"-->
	<!--		src="//themes.materializecss.com/cdn/shop/t/1/assets/Chart.js?v=28848919051585277061528498087"></script>-->
	<script
		src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
		
	<script type="text/javascript"
		src="//themes.materializecss.com/cdn/shop/t/1/assets/Chart.Financial.js?v=34644991646752552951528498109"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.7.0/fullcalendar.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.js"></script>
	<script
		src="//themes.materializecss.com/cdn/shop/t/1/assets/imagesloaded.pkgd.min.js?v=58819771796763510541528498326"></script>
	<!--画面配置-->
	<script
		src="//themes.materializecss.com/cdn/shop/t/1/assets/masonry.pkgd.min.js?v=180312904682597569011528498229"></script>

	<!-- Initialization script -->
	<script
		src="//themes.materializecss.com/cdn/shop/t/1/assets/dashboard.min.js?v=4816808830627109061528498722"></script>

</body>
</html>