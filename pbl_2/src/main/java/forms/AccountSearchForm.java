package forms;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSearchForm {
    private String name;
    private String email;
    private String[] authorities;

    // デフォルトコンストラクタ（セッション復元時などに使用）
    public AccountSearchForm() {
        this.name = "";
        this.email = "";
        this.authorities = new String[0];
    }

    // HttpServletRequest からの取得
    public AccountSearchForm(HttpServletRequest request) {
        this.name = request.getParameter("name") != null ? request.getParameter("name") : "";
        this.email = request.getParameter("email") != null ? request.getParameter("email") : "";
        this.authorities = request.getParameterValues("authorities");
        if (this.authorities == null) {
            this.authorities = new String[0];
        }
    }
}
