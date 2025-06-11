package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import services.AccountService;

@WebServlet("/C0042Servlet")
public class C0042Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		if (idStr != null) {
			try {
				int id = Integer.parseInt(idStr);
				AccountService service = new AccountService();
				Account account = service.selectById(id);
				System.out.println("取得したアカウント: " + account);
				request.setAttribute("account", account);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("C0042.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String idStr = request.getParameter("accountId");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String[] authorities = request.getParameterValues("authorities");

		Map<String, String> errors = new HashMap<>();

		if (name == null || name.isBlank()) {
			errors.put("name", "氏名を入力してください。");
		} else {
			if (name.getBytes("UTF-8").length >= 21) {
				errors.put("name", "氏名が長すぎます（20バイト以内）。");
			}
		}

		if (email == null || email.isBlank()) {
			errors.put("email", "メールアドレスを入力してください。");
		} else {
			if (email.getBytes("UTF-8").length >= 101) {
				errors.put("email", "メールアドレスが長すぎます（100バイト以内）。");
			}
		}

		if (password == null || password.isBlank()) {
			errors.put("password", "パスワードを入力してください。");
		} else if (password.getBytes("UTF-8").length >= 31) {
			errors.put("password", "パスワードが長すぎます（30バイト以内）。");
		}

		if (passwordConfirm == null || passwordConfirm.isBlank()) {
			errors.put("passwordConfirm", "パスワード（確認）を入力してください。");
		} else if (!password.equals(passwordConfirm)) {
			errors.put("passwordConfirm", "パスワードが一致していません。");
		}

		if(authorities == null || authorities.length == 0) {
			errors.put("authorities", "権限を選択してください。");
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("param.name", name);
			request.setAttribute("param.email", email);
			request.setAttribute("paramValues.authorities", authorities);
			request.getRequestDispatcher("C0042.jsp").forward(request, response);
			return;
		}

		request.setAttribute("accountId", idStr);
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		request.setAttribute("authorities", authorities);
		request.getRequestDispatcher("C0043.jsp").forward(request, response);
	}
}
