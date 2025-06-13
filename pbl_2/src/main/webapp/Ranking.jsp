<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <title>売上ダッシュボード</title>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
  <h2>担当者別 売上グラフ</h2>
  <canvas id="salesChart" width="600" height="400"></canvas>

  <script>
    const labels = [<% for (String name : (List<String>)request.getAttribute("accountNames")) { %>
      "<%= name %>",
    <% } %>];

    const data = [<% for (Integer value : (List<Integer>)request.getAttribute("salesTotals")) { %>
      <%= value %>,
    <% } %>];

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
</body>
</html>
