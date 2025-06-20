package forms;

import java.io.Serializable;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSearchForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String[] authorities;

    public AccountSearchForm() {
        this.name = "";
        this.email = "";
        this.authorities = new String[0];
    }

    public AccountSearchForm(HttpServletRequest request) {
        this.name = request.getParameter("name") != null ? request.getParameter("name") : "";
        this.email = request.getParameter("email") != null ? request.getParameter("email") : "";
        this.authorities = request.getParameterValues("authorities");
        if (this.authorities == null) {
            this.authorities = new String[0];
        }
    }
}
