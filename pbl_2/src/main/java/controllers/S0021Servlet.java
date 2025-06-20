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
import forms.SaleSearchForm;
import services.AccountService;
import services.CategoryService;
import services.SaleService;
import utils.CommonUtil;

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
		SaleSearchForm saleSearchForm = (SaleSearchForm) session.getAttribute("saleSearchForm");
		List<Sale> sales = new ArrayList<>();
		if(saleSearchForm != null) {
			sales = (new SaleService()).searchSales(saleSearchForm);
		}
		
		// 販売日 2015-01-15 を 2015/01/15 に変更
	    List<String> formattedDates = sales.stream()
	    		.map(s -> CommonUtil.formatLocDateToStr(s.getSaleDate()))
	    		.collect(Collectors.toList());
	    
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
		
		request.setAttribute("saleList", sales);
		request.setAttribute("formattedDates", formattedDates);
		request.setAttribute("accountMap", accountMap);
		request.setAttribute("categoryMap", categoryMap);
		request.getRequestDispatcher("/S0021.jsp").forward(request, response);
	}

}
