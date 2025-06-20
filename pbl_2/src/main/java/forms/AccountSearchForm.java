package forms;

import java.io.Serializable;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSearchForm implements Serializable {
    private static final long serialVersionUID = 1L;

    // S0040, S0041 で使用される検索条件（氏名／メール／権限）
    private String name;
    private String email;
    private String[] authorities;

    /**
     * 検索フォームの初期値を設定（セッション未使用時）
     * S0040Servlet の初期表示などで使用
     */
    public AccountSearchForm() {
        this.name = "";
        this.email = "";
        this.authorities = new String[0];
    }

    /**
     * リクエストから検索条件を取得してフォームに格納
     * S0040Servlet（POST処理）で使用され、セッション保存される
     */
    public AccountSearchForm(HttpServletRequest request) {
        this.name = request.getParameter("name") != null ? request.getParameter("name") : "";
        this.email = request.getParameter("email") != null ? request.getParameter("email") : "";
        this.authorities = request.getParameterValues("authorities");
        if (this.authorities == null) {
            this.authorities = new String[0];
        }
    }
}
