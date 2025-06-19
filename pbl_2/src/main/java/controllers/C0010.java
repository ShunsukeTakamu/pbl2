package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Login;
import utils.Db;

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

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("mail", mail);
			RequestDispatcher dispatcher = request.getRequestDispatcher("C0010.jsp");
			dispatcher.forward(request, response);
			return;
		}

		try (Connection conn = Db.open()) {
			String sql = "SELECT account_id, name, authority, password FROM accounts WHERE mail = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, mail);

				try (ResultSet rs = stmt.executeQuery()) {
					if (!rs.next()) {
						errors.add("メールアドレス、パスワードを正しく入力して下さい");
						request.setAttribute("errors", errors);
						request.setAttribute("mail", mail);
						RequestDispatcher dispatcher = request.getRequestDispatcher("C0010.jsp");
						dispatcher.forward(request, response);
						return;
					}

					String dbPassword = rs.getString("password");
					if (!dbPassword.equals(password)) {
						errors.add("メールアドレス、パスワードを正しく入力して下さい");
						request.setAttribute("errors", errors);
						request.setAttribute("mail", mail);
						RequestDispatcher dispatcher = request.getRequestDispatcher("C0010.jsp");
						dispatcher.forward(request, response);
						return;
					}

					HttpSession session = request.getSession();
					Login login = new Login(
							rs.getInt("account_id"),
							rs.getString("name"),
							mail,
							dbPassword,
							rs.getString("authority"));
					session.setAttribute("account", login);

					byte[] authorityBytes = rs.getBytes("authority");
					int authorityInt = authorityBytes[0] & 0xFF;
					session.setAttribute("loginAuthority", authorityInt);

					response.sendRedirect("C0020.html");
				}
			}
			//DB操作中に発生した例外（接続失敗、SQL文ミスなど）をキャッチして、
			//Servletとして正しくハンドリングするためのもの
		} catch (Exception e) {
			throw new ServletException("DBエラー", e);
		}
	}
}
