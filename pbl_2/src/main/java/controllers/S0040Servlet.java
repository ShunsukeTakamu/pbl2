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

		// フォームオブジェクト生成
		AccountSearchForm form = new AccountSearchForm(request);

		// バリデーション
		Map<String, String> errors = AccountValidation.validate(form.getName(), form.getEmail());
		if (!errors.isEmpty()) {
			request.setAttribute("error", String.join("/", errors.values()));
			request.getRequestDispatcher("/S0040.jsp").forward(request, response);
			return;
		}

		// セッションに検索条件保存
		SessionUtil.saveSearchAccount(request.getSession(), form);

		// 検索処理へリダイレクト
		response.sendRedirect("S0041.html");
	}
}
