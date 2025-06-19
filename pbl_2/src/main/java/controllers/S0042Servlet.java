package controllers;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import services.AccountService;
import services.AccountValidation;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
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
				//				System.out.println("取得したアカウント: " + account);
				request.setAttribute("account", account);

				if (account.getAuthority() != null && account.getAuthority().length > 0) {
					int authVal = account.getAuthority()[0] & 0xFF; // byteをunsigned int に変換
					request.setAttribute("authVal", authVal);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("S0042.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		String idStr = request.getParameter("accountId");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String[] authorities = request.getParameterValues("authorities");

		if ("back".equals(action)) {
			request.setAttribute("accountId", idStr);
			request.setAttribute("param.name", name);
			request.setAttribute("param.email", email);
			request.setAttribute("paramAuthorities", authorities);
			request.getRequestDispatcher("S0042.jsp").forward(request, response);
			return;
		}
		Map<String, String> errors = AccountValidation.validateForEdit(
			    name, email, password, passwordConfirm, authorities
			);
			if (!errors.isEmpty()) {
			    request.setAttribute("errors", errors);
			    request.setAttribute("accountId", idStr);
			    request.setAttribute("param.name", name);
			    request.setAttribute("param.email", email);
			    request.setAttribute("authorities", authorities);
			    request.getRequestDispatcher("S0042.jsp").forward(request, response);
			    return;
			}

			Map<String, Boolean> flags = AccountValidation.resolveAuthorityFlags(authorities);
			flags.forEach(request::setAttribute);

		
		request.setAttribute("accountId", idStr);
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		request.setAttribute("authorities", authorities);

		if ("delete".equals(action)) {
			request.getRequestDispatcher("S0044.jsp").forward(request, response);
		} else {

			request.getRequestDispatcher("S0043.jsp").forward(request, response);
		}
	}
}
