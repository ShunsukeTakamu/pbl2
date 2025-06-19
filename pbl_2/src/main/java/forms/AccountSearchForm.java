package forms;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountSearchForm {
    private final String name;
    private final String email;
    private final String[] authorities;

    public AccountSearchForm(HttpServletRequest request) {
        this.name = request.getParameter("name");
        this.email = request.getParameter("email");
        this.authorities = request.getParameterValues("authorities");
    }
}
