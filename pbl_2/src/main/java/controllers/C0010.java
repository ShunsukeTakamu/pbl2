package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Account;
import beans.Login;
import services.AccountService;

@WebServlet("/C0010.html")
public class C0010 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C0010() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("C0010.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");

		List<String> errors = new ArrayList<>();

		// バリデーション
		if (mail == null || mail.trim().isEmpty()) {
			errors.add("メールアドレスを入力してください。");
		} else if (mail.getBytes("UTF-8").length >= 101) {
			errors.add("メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{1,}$")) {
			errors.add("メールアドレスを正しく入力してください。");
		}

		if (password == null || password.trim().isEmpty()) {
			errors.add("パスワードが未入力です");
		} else if (password.getBytes("UTF-8").length >= 31) {
			errors.add("パスワードが長すぎます");
		}

		// バリデーションエラー
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("mail", mail);
			RequestDispatcher dispatcher = request.getRequestDispatcher("C0010.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// アカウント検索・認証
		AccountService service = new AccountService();
		Account account = service.findValidByMail(mail);

		if (account == null || !account.getPassword().equals(password)) {
			errors.add("メールアドレス、パスワードを正しく入力して下さい");
			request.setAttribute("errors", errors);
			request.setAttribute("mail", mail);
			RequestDispatcher dispatcher = request.getRequestDispatcher("C0010.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// 認証成功
		HttpSession session = request.getSession();
		Login login = new Login(
				account.getAccountId(),
				account.getName(),
				account.getMail(),
				account.getPassword(),
				new String(account.getAuthority()) // or 適切な形式で
		);
		session.setAttribute("account", login);

		// 権限ビットをintとして格納
		int authorityInt = account.getAuthority()[0] & 0xFF;
		session.setAttribute("loginAuthority", authorityInt);

		// ダッシュボードへ
		response.sendRedirect("C0020.html");
	}
}
