package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import forms.AccountSearchForm;
import services.AccountService;
import utils.SessionUtil;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// セッションからフォームデータを取得
		AccountSearchForm form = SessionUtil.getSearchAccount(request.getSession());

		// サービス呼び出し
		List<Account> accounts = new AccountService().searchAccounts(form.getName(), form.getEmail(), form.getAuthorities());

		// 結果なしチェック
		if (accounts == null || accounts.isEmpty()) {
			request.setAttribute("noResult", "該当するアカウントがありませんでした。");
			request.getRequestDispatcher("/S0040.jsp").forward(request, response);
			return;
		}

		// 結果・検索条件をリクエストにセット
		request.setAttribute("accounts", accounts);
		request.setAttribute("name", form.getName());
		request.setAttribute("email", form.getEmail());
		request.setAttribute("authorities", form.getAuthorities());

		request.getRequestDispatcher("/S0041.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
