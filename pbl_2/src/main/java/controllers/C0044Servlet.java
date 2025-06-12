package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.AccountService;

@WebServlet("/C0044Servlet")
public class C0044Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idStr = request.getParameter("id");
		if (idStr != null) {
			try {
				int id = Integer.parseInt(idStr);
				AccountService service = new AccountService();
				var account = service.selectById(id);

				if (account != null) {
					byte[] auth = account.getAuthority();
					int authValue = auth[0] & 0xFF;

					request.setAttribute("accountId", account.getAccountId());
					request.setAttribute("name", account.getName());
					request.setAttribute("email", account.getMail());
					request.setAttribute("password", account.getPassword());
					request.setAttribute("authorities", new String[]{String.valueOf(authValue)});
					request.setAttribute("has0", authValue == 0);
					request.setAttribute("has1", (authValue & 1) != 0);
					request.setAttribute("has2", (authValue & 2) != 0);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("C0044.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accountId = request.getParameter("accountId");

		try {
			int id = Integer.parseInt(accountId);
			AccountService service = new AccountService();
			service.delete(id);
			response.sendRedirect("C0041Servlet");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "アカウント削除に失敗しました。");
			
		}
	}
}
