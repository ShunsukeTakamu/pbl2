package forms;

import jakarta.servlet.http.HttpServletRequest;

import beans.Account;
import lombok.Getter;

@Getter
public class AccountEditForm {
	private final String accountId;
    private final String name;
    private final String email;
    private final String password;
    private final String passwordConfirm;
    private final String[] authorities;

    public AccountEditForm(HttpServletRequest request) {
    	this.accountId = request.getParameter("accountId");
        this.name = request.getParameter("name");
        this.email = request.getParameter("email");
        this.password = request.getParameter("password");
        this.passwordConfirm = request.getParameter("passwordConfirm");
        this.authorities = request.getParameterValues("authorities");
    }
    
    // S0043
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
