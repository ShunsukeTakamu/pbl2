package controllers;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginFilter
 */
@WebFilter("/*") // 適用するURLパターン
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();

        // 除外対象URL例
        if(path.equals(contextPath + "/C0010.jsp") ||   // ログイン画面
           path.equals(contextPath + "/C0010.html") ||  // ログイン処理（POST先）
           path.equals(contextPath + "/logout.jsp") ||  // ログアウト画面
           path.startsWith(contextPath + "/css/") ||    // CSS
           path.startsWith(contextPath + "/js/")) {     // JS
           chain.doFilter(request, response);
           return;
        }

        HttpSession session = request.getSession(false);

        // ログイン情報チェック
        if (session == null || session.getAttribute("account") == null) {
            // 未ログインならログイン画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/C0010.jsp");
            return;
        }
     
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        chain.doFilter(request, response); // 通過
    }
}