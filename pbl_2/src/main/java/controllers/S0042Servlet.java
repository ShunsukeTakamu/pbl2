package controllers;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import forms.AccountEditForm;
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

				if (account != null) {
					request.setAttribute("account", account);

					if (account.getAuthority() != null && account.getAuthority().length > 0) {
						int authVal = account.getAuthority()[0] & 0xFF;
						request.setAttribute("authVal", authVal);
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace(); // TODO: ログ管理に変更可
			}
		}
		request.getRequestDispatcher("S0042.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String accountId = request.getParameter("accountId");

		AccountEditForm form = new AccountEditForm(request);

		// 戻るボタンの処理（バリデーション不要）
		if ("back".equals(action)) {
			setFormAttributes(request, form, accountId);
			request.getRequestDispatcher("S0042.jsp").forward(request, response);
			return;
		}

		// バリデーション
		Map<String, String> errors = AccountValidation.validateForEdit(form);
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			setFormAttributes(request, form, accountId);
			request.getRequestDispatcher("S0042.jsp").forward(request, response);
			return;
		}

		// 権限ビットチェック用のフラグをセット
		Map<String, Boolean> flags = AccountValidation.resolveAuthorityFlags(form.getAuthorities());
		flags.forEach(request::setAttribute);

		// 確認画面用の引き継ぎ
		setFormAttributes(request, form, accountId);

		// 遷移先の分岐
		if ("delete".equals(action)) {
			request.getRequestDispatcher("S0044.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("S0043.jsp").forward(request, response);
		}
	}

	// ★フォーム項目とアカウントIDをリクエスト属性にセットする共通メソッド
	private void setFormAttributes(HttpServletRequest request, AccountEditForm form, String accountId) {
		request.setAttribute("accountId", accountId);
		request.setAttribute("name", form.getName());
		request.setAttribute("email", form.getEmail());
		request.setAttribute("password", form.getPassword());
		request.setAttribute("passwordConfirm", form.getPasswordConfirm());
		request.setAttribute("authorities", form.getAuthorities());
	}
}
