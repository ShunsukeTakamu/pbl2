package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import forms.AccountEditForm;
import services.AccountService;

/**
 * Servlet implementation class S0043Servlet
 */
@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0043Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    request.setCharacterEncoding("UTF-8");

	    try {
	        AccountEditForm form = new AccountEditForm(request);
	        Account updated = form.toAccount();

	        new AccountService().update(updated);

	        request.getSession().setAttribute("update", "アカウントが更新されました。");
	        response.sendRedirect("S0041.html");

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "アカウントの更新に失敗しました。");
	        request.getRequestDispatcher("S0042.jsp").forward(request, response); // ←.jsp に修正
	    }
	}


}
