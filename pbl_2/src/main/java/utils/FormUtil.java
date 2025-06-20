package utils;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import beans.Account;
import forms.AccountEditForm;
import forms.AccountSearchForm;

public class FormUtil {

	// S0040
	public static void setAccountSearchAttributes(HttpServletRequest request, AccountSearchForm form,
			List<Account> accounts) {
		request.setAttribute("accounts", accounts);
		request.setAttribute("name", form.getName());
		request.setAttribute("email", form.getEmail());
		request.setAttribute("authorities", form.getAuthorities());
	}

	// S0042 アカウント編集フォームの内容をリクエスト属性にセットする
	public static void setAccountFormAttributes(HttpServletRequest request, AccountEditForm form, String accountId) {
		request.setAttribute("accountId", accountId);
		request.setAttribute("name", form.getName());
		request.setAttribute("email", form.getEmail());
		request.setAttribute("password", form.getPassword());
		request.setAttribute("passwordConfirm", form.getPasswordConfirm());
		request.setAttribute("authorities", form.getAuthorities());
	}
}
