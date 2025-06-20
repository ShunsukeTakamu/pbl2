package forms;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountForm {
    private final String name;
    private final String mail;
    private final String password;
    private final String confirmPassword;
    private final String[] authorities;

    public AccountForm(HttpServletRequest request) {
        this.name = request.getParameter("name");
        this.mail = request.getParameter("mail");
        this.password = request.getParameter("password");
        this.confirmPassword = request.getParameter("confirmPassword");
        this.authorities = request.getParameterValues("authorities");
    }
}
