package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		Account loginUser = (Account) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("C0010.jsp");
			return;
		}

		// 権限チェック
		byte[] auth = loginUser.getAuthority();
		if (auth == null || auth.length == 0 || (auth[0] & 0b10) == 0) {
			request.setAttribute("accessDenied", true);
			request.getRequestDispatcher("C0042.jsp").forward(request, response);
			return;
		}

		String idStr = request.getParameter("accountId");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String authorityRadio = request.getParameter("authority");
		String[] authorities = request.getParameterValues("authorities");

		Map<String, String> errors = new HashMap<>();
		if (name == null || name.isBlank())
			errors.put("name", "氏名は必須です。");
		if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$"))
			errors.put("email", "正しいメールアドレスを入力してください。");
		if (password == null || password.length() < 4)
			errors.put("password", "パスワードを4文字以上で入力してください。");
		if (passwordConfirm == null || !password.equals(passwordConfirm))
			errors.put("passwordConfirm", "パスワードが一致しません。");

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

		response.sendRedirect("C0040.jsp"); // アカウント検索へ戻る
	}
}
