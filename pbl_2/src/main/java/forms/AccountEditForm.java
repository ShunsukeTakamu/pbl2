package forms;

import jakarta.servlet.http.HttpServletRequest;

import beans.Account;
import lombok.Getter;

@Getter
public class AccountEditForm {

	// S0042, S0043 で使用：アカウント更新時に hidden で渡される ID
	private final String accountId;

	// S0042, S0043 で使用：フォームから取得する各項目
    private final String name;
    private final String email;
    private final String password;
    private final String passwordConfirm;
    private final String[] authorities;

    /**
     * フォームの各値をリクエストから取得（S0042, S0043 の POST 処理で使用）
     */
    public AccountEditForm(HttpServletRequest request) {
    	this.accountId = request.getParameter("accountId");
        this.name = request.getParameter("name");
        this.email = request.getParameter("email");
        this.password = request.getParameter("password");
        this.passwordConfirm = request.getParameter("passwordConfirm");
        this.authorities = request.getParameterValues("authorities");
    }
    
    /**
     * フォーム内容を Account エンティティに変換（S0043Servlet にて DB更新用に使用）
     */
    public Account toAccount() {
        byte authorityByte = 0;
        if (authorities != null) {
            for (String a : authorities) {
                authorityByte |= Integer.parseInt(a);
            }
        }

        Account account = new Account();
        account.setAccountId(Integer.parseInt(accountId));
        account.setName(name);
        account.setMail(email);
        account.setPassword(password);
        account.setAuthority(new byte[]{authorityByte});

        return account;
    }
}
