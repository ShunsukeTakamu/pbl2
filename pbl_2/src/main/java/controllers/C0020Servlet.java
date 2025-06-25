package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.AccountService;
import services.SaleService;

@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SaleService saleService = new SaleService();

		// アカウント別売上
		Map<String, Integer> salesMap = saleService.getSalesByAccount();
		request.setAttribute("accountNames", new ArrayList<>(salesMap.keySet()));
		request.setAttribute("salesTotals", new ArrayList<>(salesMap.values()));

		// 年別売上推移
		Map<String, Integer> yearMap = saleService.getSalesByYear();
		request.setAttribute("saleYears", new ArrayList<>(yearMap.keySet()));
		request.setAttribute("yearlyTotals", new ArrayList<>(yearMap.values()));

		// 今年 vs 昨年 売上比較
		int totalSales = saleService.getCurrentYearSales();
		int previousSales = saleService.getPreviousYearSales();
		int changeAmount = totalSales - previousSales;
		double changeRate = previousSales == 0 ? 0 : (double) changeAmount / previousSales * 100;
		boolean isIncrease = changeAmount >= 0;

		request.setAttribute("totalSales", totalSales);
		request.setAttribute("changeRate", Math.abs(changeRate));
		request.setAttribute("isIncrease", isIncrease);

		// 総売上
		int allTimeSales = saleService.getTotalSales();
		request.setAttribute("allTimeSales", allTimeSales);

		// カテゴリー別売上
		Map<String, Integer> categoryMap = saleService.getSalesByCategory();
		request.setAttribute("categoryNames", new ArrayList<>(categoryMap.keySet()));
		request.setAttribute("categoryTotals", new ArrayList<>(categoryMap.values()));

		// 登録アカウント数
		int accountCount = new AccountService().getAccountCount();
		request.setAttribute("accountCount", accountCount);

		request.getRequestDispatcher("/C0020.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}
}
