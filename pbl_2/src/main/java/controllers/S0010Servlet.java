package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import forms.SaleForm;
import services.AccountService;
import services.CategoryService;
import services.SaleValidation;
import utils.ReturnPair;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0010Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("sale");
		LocalDate today = LocalDate.now();
		SaleForm saleForm = new SaleForm(today, 0, 0, -1, -1);
		ArrayList<Account> accounts = (new AccountService()).selectAll();
		ArrayList<Category> categories = (new CategoryService()).selectAll();
		
		request.setAttribute("saleForm", saleForm);
		request.setAttribute("accounts", accounts);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // パラメータ取得
        SaleForm saleForm = new SaleForm(request);
        
        ReturnPair returnPair = SaleValidation.validate(saleForm);
        Sale sale = returnPair.getSale();
        Map<String, String> errors = returnPair.getErrors();
        
        // エラーがあれば戻る
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("sale", sale);
            request.setAttribute("accounts", (new AccountService()).selectAll());
            request.setAttribute("categories", (new CategoryService()).selectAll());
            request.getRequestDispatcher("/S0010.jsp").forward(request, response);
            return;
        }

        // 正常時 → 確認画面へ
        HttpSession session = request.getSession();
        session.setAttribute("sale", sale);
        response.sendRedirect("S0011.html");
	}

}
