package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import services.AccountService;

/**
 * Servlet implementation class S0043Servlet
 */
@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0043Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			// パラメータ取得
			int accountId = Integer.parseInt(request.getParameter("accountId"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String oldPassword = request.getParameter("oldPassword");
			if(password == null || password.isBlank()) {
				password = oldPassword;
			}
			String[] authorities = request.getParameterValues("authorities");

			// 権限値の計算（bit演算）
			byte authorityByte = 0;
			if (authorities != null) {
				for (String a : authorities) {
					authorityByte |= Integer.parseInt(a);
				}
			}

			Account updated = new Account();
			updated.setAccountId(accountId);
			updated.setName(name);
			updated.setMail(email);
			updated.setPassword(password); // 必要に応じてハッシュ化
			updated.setAuthority(new byte[] { authorityByte });

			AccountService service = new AccountService();
			service.update(updated);

			request.getSession().setAttribute("update", "アカウントが更新されました。");

			response.sendRedirect("S0041.html");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "アカウントの更新に失敗しました。");
			request.getRequestDispatcher("S0042.html").forward(request, response);
		}
	}

}
