package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import services.SaleValidation;

/**
 * Servlet implementation class S0020Servlet
 */
@WebServlet("/S0020.html")
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
		HttpSession session = request.getSession();
		session.removeAttribute("saleSearchForm");
		Sale sale = new Sale(0, 0);
		request.setAttribute("sale", sale);
		SaleSearchForm saleSearchForm = new SaleSearchForm(0, 0);
		ArrayList<Account> accounts = (new AccountService()).selectAll();
		ArrayList<Category> categories = (new CategoryService()).selectAll();
		
		request.setAttribute("saleSearchForm", saleSearchForm);
		request.setAttribute("accounts", accounts);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/S0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        // パラメータ取得
		SaleSearchForm saleSearchForm = new SaleSearchForm(request);

        Map<String, String> errors = SaleValidation.validate(saleSearchForm);
        
        request.setAttribute("saleSearchForm", saleSearchForm);
        request.setAttribute("accounts", (new AccountService()).selectAll());
        request.setAttribute("categories", (new CategoryService()).selectAll());
        // エラーがあれば戻る
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/S0020.jsp").forward(request, response);
            return;
        }
        
        // 件数チェック
        List<Sale> sales = (new SaleService()).searchSales(saleSearchForm);
        // 0件であれば戻る
        if (sales.isEmpty()) {
        	request.setAttribute("noResult", "検索結果はありません。");
            request.getRequestDispatcher("/S0020.jsp").forward(request, response);
            return;
        }

        // 正常時 → 検索結果画面へ
        HttpSession session = request.getSession();
	    session.setAttribute("saleSearchForm", saleSearchForm);
        response.sendRedirect("S0021.html");
	}

}
