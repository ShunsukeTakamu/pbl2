package utils;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import beans.Account;
import forms.AccountEditForm;
import forms.AccountSearchForm;

public class FormUtil {

	// S0041Servlet: アカウント検索結果画面に検索条件と結果リストを渡す
	public static void setAccountSearchAttributes(HttpServletRequest request, AccountSearchForm form,
			List<Account> accounts) {
		request.setAttribute("accounts", accounts);
		request.setAttribute("name", form.getName());
		request.setAttribute("email", form.getEmail());
		request.setAttribute("authorities", form.getAuthorities());
	}

	// S0042Servlet: 編集フォームの項目を確認画面や再表示用にリクエストへ渡す
	public static void setAccountFormAttributes(HttpServletRequest request, AccountEditForm form, String accountId) {
		request.setAttribute("accountId", accountId);
		request.setAttribute("name", form.getName());
		request.setAttribute("email", form.getEmail());
		request.setAttribute("password", form.getPassword());
		request.setAttribute("passwordConfirm", form.getPasswordConfirm());
		request.setAttribute("authorities", form.getAuthorities());

		// 入力ミスやキャンセルの際の権限を設定
		int authVal = 0;
		for (String auth : form.getAuthorities()) {
			if ("0".equals(auth)) {
				authVal = 0;
				break;
			}
			authVal |= Integer.parseInt(auth);
		}
		request.setAttribute("authVal", authVal);
	}

	// S0042Servlet / S0044Servlet: 権限ビットを個別の表示用フラグへ変換（JSP側チェックボックス制御用）
	public static void setAuthorityFlags(HttpServletRequest request, int authVal) {
		request.setAttribute("has0", authVal == 0);
		request.setAttribute("has1", (authVal & 1) != 0);
		request.setAttribute("has2", (authVal & 2) != 0);
	}

	// S0044Servlet: Accountインスタンスからリクエスト属性をセット（削除確認画面に表示用）
	public static void setAccountAttributesFromAccount(HttpServletRequest request, Account account, int authVal) {
		request.setAttribute("accountId", account.getAccountId());
		request.setAttribute("name", account.getName());
		request.setAttribute("email", account.getMail());
		request.setAttribute("password", account.getPassword());
		request.setAttribute("authorities", new String[] { String.valueOf(authVal) });
	}

}
