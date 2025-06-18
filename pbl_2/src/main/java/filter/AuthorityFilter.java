package filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthorityFilter implements Filter {

    // アクセス制限対象と必要な権限ビット
    private static final Map<String, Integer> protectedUrls = new HashMap<>();
    static {
        protectedUrls.put("/S0010.html", 1); // 売上登録が必要
        protectedUrls.put("/S0010.jsp", 1); // 売上登録が必要
        protectedUrls.put("/C0030.html", 2); // アカウント登録が必要
        protectedUrls.put("/C0030.jsp", 2); // アカウント登録が必要
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        // 静的ファイルやログイン系処理は除外（LoginFilterが処理する）
        if (path.startsWith("/css/") || path.startsWith("/js/")) {
            chain.doFilter(request, response);
            return;
        }

        // 対象外のページならスルー
        if (!protectedUrls.containsKey(path)) {
            chain.doFilter(request, response);
            return;
        }

        // ログイン済セッションから権限を取得（LoginFilterでログイン済は保証されている）
        HttpSession session = request.getSession(false);
        byte[] authority = (session != null) ? (byte[]) session.getAttribute("loginAuthority") : null;

        // 権限情報が取得できなければ拒否
        if (authority == null) {
        	request.getRequestDispatcher("/accessDenied.jsp").forward(request, response);				
            return;
        }

        int userAuth = authority[0] & 0xFF;
        int required = protectedUrls.get(path);

        if ((userAuth & required) == 0) {
        	request.getRequestDispatcher("/accessDenied.jsp").forward(request, response);
            return;
        }

        // 通過
        chain.doFilter(request, response);
    }
}
