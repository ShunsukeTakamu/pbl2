package controllers;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
 * Servlet implementation class S0020Servlet
 */
@WebServlet("/S0020Servlet")
public class S0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0020Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Account> accounts = (new AccountService()).selectAll();
		request.setAttribute("accounts", accounts);
		ArrayList<Category> categories = (new CategoryService()).selectAll();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/S0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

	    String dateStart = request.getParameter("dateStart");
	    String dateEnd = request.getParameter("dateEnd");
	    String accountId = request.getParameter("accountId");
	    String categoryId = request.getParameter("categoryId");
	    String tradeName = request.getParameter("tradeName");
	    String note = request.getParameter("note");
	    
	    List<Sale> sales = (new SaleService()).searchSales(dateStart, dateEnd, accountId, categoryId, tradeName, note);
	    session.setAttribute("saleList", sales);
	    
	    // 販売日 2015-01-15 を 2015/01/15 に変更
	    List<String> formattedDates = sales.stream()
	    		.map(sale -> sale.getSaleDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
	    		.collect(Collectors.toList());
	    session.setAttribute("formattedDates", formattedDates);
	    
	    response.sendRedirect("S0021Servlet");
	}

}
