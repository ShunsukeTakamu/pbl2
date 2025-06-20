package controllers;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import forms.AccountSearchForm;
import services.AccountValidation;
import utils.SessionUtil;

@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 初期表示で検索条件をクリア
		SessionUtil.clearSearchAccount(request.getSession());
		request.getRequestDispatcher("/S0040.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		if ("clear".equals(action)) {
			SessionUtil.clearSearchAccount(request.getSession());
			request.getRequestDispatcher("/S0040.jsp").forward(request, response);
			return;
		}

		// 通常の検索処理
		AccountSearchForm form = new AccountSearchForm(request);

		Map<String, String> errors = AccountValidation.validate(form);
		if (!errors.isEmpty()) {
			request.setAttribute("error", String.join("/", errors.values()));
			request.getRequestDispatcher("/S0040.jsp").forward(request, response);
			return;
		}

		SessionUtil.saveSearchAccount(request.getSession(), form);
		response.sendRedirect("S0041.html");
	}
}
