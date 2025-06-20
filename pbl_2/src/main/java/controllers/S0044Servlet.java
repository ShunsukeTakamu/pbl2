package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import services.AccountService;
import utils.CommonUtil;
import utils.FormUtil;

@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = CommonUtil.parseIntSafe(request.getParameter("id"), -1);

		if (id != -1) {
			AccountService service = new AccountService();
			Account account = service.selectById(id);

			if (account != null) {
				int authVal = CommonUtil.byteToUnsignedInt(account.getAuthority()[0]);
				FormUtil.setAccountAttributesFromAccount(request, account, authVal);
				FormUtil.setAuthorityFlags(request, authVal);

			}
		} else {
			request.setAttribute("error", "無効なアカウントIDです。");
		}

		request.getRequestDispatcher("S0044.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accountId = request.getParameter("accountId");

		try {
			int id = Integer.parseInt(accountId);

			AccountService service = new AccountService();
			service.deleteAccount(id);

			request.getSession().setAttribute("delete", "アカウントが削除されました。");
			response.sendRedirect("S0041.html");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "アカウント削除に失敗しました。");
			request.getRequestDispatcher("S0044.html").forward(request, response);
		}
	}
}
