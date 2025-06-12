package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010Servlet")
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
		LocalDate today = LocalDate.now();
		request.setAttribute("today", today);
		ArrayList<Category> categories = (new CategoryService()).selectAll();
		request.setAttribute("categories", categories);
		ArrayList<Account> accounts = (new AccountService()).selectAll();
		request.setAttribute("accounts", accounts);
		request.getRequestDispatcher("/S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String note = request.getParameter("note");
		// 空文字ならnoteをnullにする
		if (note.isEmpty()) {
			note = null;
		}
		Sale sale = new Sale(
				0,
				LocalDate.parse(request.getParameter("saleDate")),
				Integer.parseInt(request.getParameter("accountId")),
				Integer.parseInt(request.getParameter("categoryId")),
				request.getParameter("tradeName"),
				Integer.parseInt(request.getParameter("unitPrice")),
				Integer.parseInt(request.getParameter("saleNumber")),
				note);
		session.setAttribute("sale", sale);
		response.sendRedirect("S0011Servlet");
	}

}
