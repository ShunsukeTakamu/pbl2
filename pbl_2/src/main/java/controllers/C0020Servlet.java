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

/**
 * Servlet implementation class C0020Servlet
 */
@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public C0020Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SaleService saleService = new SaleService();
		// アカウント別
		Map<String, Integer> salesMap = saleService.getSalesByAccount();
		request.setAttribute("accountNames", new ArrayList<>(salesMap.keySet()));
		request.setAttribute("salesTotals", new ArrayList<>(salesMap.values()));
		// 年別推移
		Map<String, Integer> yearMap = saleService.getSalesByYear();
		request.setAttribute("saleYears", new ArrayList<>(yearMap.keySet()));
		request.setAttribute("yearlyTotals", new ArrayList<>(yearMap.values()));

		// 今年の売上と前年の売上を取得して比較
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

		// カテゴリー別
		Map<String, Integer> categoryMap = saleService.getSalesByCategory();
		request.setAttribute("categoryNames", new ArrayList<>(categoryMap.keySet()));
		request.setAttribute("categoryTotals", new ArrayList<>(categoryMap.values()));

		AccountService accountService = new AccountService();
		int accountCount = accountService.getAccountCount();
		request.setAttribute("accountCount", accountCount);

		request.getRequestDispatcher("/C0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
