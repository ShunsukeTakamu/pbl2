package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
import services.AccountValidation;

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

		// バリデーション共通化
	    Map<String, String> errors = AccountValidation.validateForLogin(mail, password);

	    if (!errors.isEmpty()) {
	        request.setAttribute("errors", new ArrayList<>(errors.values()));
	        request.setAttribute("mail", mail);
	        request.getRequestDispatcher("C0010.jsp").forward(request, response);
	        return;
	    }

		// アカウント検索・認証
		AccountService service = new AccountService();
		Account account = service.findValidByMail(mail);

		if (account == null || !account.getPassword().equals(password)) {
			var errorList = new ArrayList<String>();
			errorList.add("メールアドレス、パスワードを正しく入力して下さい");
			request.setAttribute("errors", errorList);
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
				account.getEmail(),
				account.getPassword(),
				new String(account.getAuthority())
		);
		session.setAttribute("account", login);

		// 権限ビットをintとして格納
		int authorityInt = account.getAuthority()[0] & 0xFF;
		session.setAttribute("loginAuthority", authorityInt);

		// ダッシュボードへ
		response.sendRedirect("C0020.html");
	}
}
