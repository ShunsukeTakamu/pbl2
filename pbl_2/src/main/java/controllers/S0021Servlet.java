package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Account;
import beans.Category;
import beans.Sale;
import services.AccountService;
import services.CategoryService;
import services.SaleService;

/**
 * Servlet implementation class S0021Servlet
 */
@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0021Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String dateStart = (String) session.getAttribute("dateStart");
		String dateEnd = (String) session.getAttribute("dateEnd");
		String accountIdStr = (String) session.getAttribute("accountIdStr");
		String categoryIdStr = (String) session.getAttribute("categoryIdStr");
		String tradeName = (String) session.getAttribute("partOfTradeName");
		String note = (String) session.getAttribute("partOfNote");
		
		ArrayList<Account> accounts = (new AccountService()).selectAll();
		ArrayList<Category> categories = (new CategoryService()).selectAll();
		// idとnameのMapに変換
		Map<Integer, String> accountMap = accounts.stream()
				.collect(Collectors.toMap(
						a -> a.getAccountId(),
						a -> a.getName()));
		Map<Integer, String> categoryMap = categories.stream()
				.collect(Collectors.toMap(
						c -> c.getCategoryId(),
						c -> c.getCategoryName()));
		request.setAttribute("accountMap", accountMap);
		request.setAttribute("categoryMap", categoryMap);
		
		List<Sale> sales = (new SaleService()).searchSales(dateStart, dateEnd, accountIdStr, categoryIdStr, tradeName, note);
		request.setAttribute("saleList", sales);
		request.getRequestDispatcher("/S0021.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
