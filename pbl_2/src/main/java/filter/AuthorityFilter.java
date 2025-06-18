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

    private static final Map<String, Integer> protectedUrls = new HashMap<>();
    static {
        protectedUrls.put("/S0010.html", 1);
        protectedUrls.put("/S0010.jsp", 1);
        protectedUrls.put("/S0030.html", 2);
        protectedUrls.put("/S0030.jsp", 2);
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        // 除外パス
        if (path.startsWith("/css/") || path.startsWith("/js/")) {
            chain.doFilter(request, response);
            return;
        }

        // ログ出力で調査
        System.out.println("[AuthorityFilter] URI: " + uri);
        System.out.println("[AuthorityFilter] path: " + path);
        System.out.println("[AuthorityFilter] is protected: " + protectedUrls.containsKey(path));

        if (!protectedUrls.containsKey(path)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        byte[] authority = (session != null) ? (byte[]) session.getAttribute("loginAuthority") : null;

        if (authority == null) {
            System.out.println("[AuthorityFilter] loginAuthority is null");
            response.sendRedirect(contextPath + "/accessDenied.jsp");
            return;
        }

        int userAuth = authority[0] & 0xFF;
        int required = protectedUrls.get(path);

        System.out.println("[AuthorityFilter] userAuth: " + userAuth + ", required: " + required);

        if ((userAuth & required) == 0) {
            System.out.println("[AuthorityFilter] 権限が不足しています。");
            response.sendRedirect(contextPath + "/accessDenied.jsp");
            return;
        }

        // 権限OK
        chain.doFilter(request, response);
    }
}
