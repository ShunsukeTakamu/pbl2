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
				System.out.println("取得したアカウント: " + account); // ★追加
				request.setAttribute("account", account);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher("C0042.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		HttpSession session = request.getSession();
//		Account loginUser = (Account) session.getAttribute("loginUser");
//
//		if (loginUser == null) {
//			response.sendRedirect("C0010.jsp");
//			return;
//		}
//
//		// 権限チェック
//		byte[] auth = loginUser.getAuthority();
//		if (auth == null || auth.length == 0 || (auth[0] & 0b10) == 0) {
//			request.setAttribute("accessDenied", true);
//			request.getRequestDispatcher("C0042.jsp").forward(request, response);
//			return;
//		}

		String idStr = request.getParameter("accountId");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String authorityRadio = request.getParameter("authority");
		String[] authorities = request.getParameterValues("authorities");

		Map<String, String> errors = new HashMap<>();

		// 1-1 氏名必須チェック
		if (name == null || name.isBlank()) {
			errors.put("name", "氏名を入力して下さい。");
		} else {
			// 1-2 氏名バイト長チェック（UTF-8）
			try {
				if (name.getBytes("UTF-8").length >= 21) {
					errors.put("name", "氏名が長すぎます。");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 1-3 メールアドレス必須チェック
		if (email == null || email.isBlank()) {
			errors.put("email", "メールアドレスを入力して下さい。");
		} else {
			// 1-4 メールアドレス長さチェック
			try {
				if (email.getBytes("UTF-8").length >= 101) {
					errors.put("email", "メールアドレスが長すぎます。");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 1-6 パスワード必須チェック
		if (password == null || password.isBlank()) {
			errors.put("password", "パスワードを入力して下さい。");
		} else {
			// 1-7 パスワード長さチェック
			try {
				if (password.getBytes("UTF-8").length >= 31) {
					errors.put("password", "パスワードが長すぎます。");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 1-8 パスワード（確認）必須チェック
		if (passwordConfirm == null || passwordConfirm.isBlank()) {
			errors.put("passwordConfirm", "パスワード（確認）を入力して下さい。");
		} else {
			// 1-9 パスワード一致チェック
			if (password != null && !password.equals(passwordConfirm)) {
				errors.put("passwordConfirm", "パスワードとパスワード（確認）が一致していません。");
			}
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("param.name", name);
			request.setAttribute("param.email", email);
			request.setAttribute("param.authority", authorityRadio);
			request.setAttribute("paramValues.authorities", authorities);
			request.getRequestDispatcher("C0042.jsp").forward(request, response);
			return;
		}

		int accountId = Integer.parseInt(idStr);
		byte authorityByte = 0;

		if ("0".equals(authorityRadio)) {
			authorityByte = 0;
		} else {
			if (authorities != null) {
				for (String a : authorities) {
					authorityByte |= Integer.parseInt(a);
				}
			}
		}

		Account updated = new Account();
		updated.setAccountId(accountId);
		updated.setName(name);
		updated.setMail(email);
		updated.setPassword(password);
		updated.setAuthority(new byte[] { authorityByte });

		AccountService service = new AccountService();
		service.update(updated);

		response.sendRedirect("C0041.jsp"); // アカウント検索へ戻る
	}
}
