package forms;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Getter;

@Getter
public class AccountEditForm {
    private final String name;
    private final String email;
    private final String password;
    private final String passwordConfirm;
    private final String[] authorities;

    public AccountEditForm(HttpServletRequest request) {
        this.name = request.getParameter("name");
        this.email = request.getParameter("email");
        this.password = request.getParameter("password");
        this.passwordConfirm = request.getParameter("passwordConfirm");
        this.authorities = request.getParameterValues("authorities");
    }
}
