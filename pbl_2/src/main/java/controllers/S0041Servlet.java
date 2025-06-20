package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import forms.AccountSearchForm;
import services.AccountService;
import services.AccountValidation;
import utils.SessionUtil;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// セッションから検索条件を取得
		AccountSearchForm form = SessionUtil.getSearchAccount(request.getSession());

		// オプション：不正セッション対策バリデーション
		Map<String, String> errors = AccountValidation.validateForResult(form);
		if (!errors.isEmpty()) {
			request.setAttribute("error", String.join("/", errors.values()));
			request.getRequestDispatcher("/S0040.jsp").forward(request, response);
			return;
		}

		// 検索実行
		List<Account> accounts = new AccountService().searchAccounts(form.getName(), form.getEmail(),
				form.getAuthorities());

		if (accounts == null || accounts.isEmpty()) {
			request.setAttribute("noResult", "該当するアカウントがありませんでした。");
			request.getRequestDispatcher("/S0040.jsp").forward(request, response);
			return;
		}

		request.setAttribute("accounts", accounts);
		request.setAttribute("name", form.getName());
		request.setAttribute("email", form.getEmail());
		request.setAttribute("authorities", form.getAuthorities());

		request.getRequestDispatcher("/S0041.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
