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
        protectedUrls.put("/S0010.html", 1); // 売上権限が必要（01）
        protectedUrls.put("/S0010.jsp", 1);
        protectedUrls.put("/S0011.html", 1);
        protectedUrls.put("/S0011.jsp", 1);
        protectedUrls.put("/S0023.html", 1);
        protectedUrls.put("/S0023.jsp", 1);
        protectedUrls.put("/S0024.html", 1);
        protectedUrls.put("/S0024.jsp", 1);
        protectedUrls.put("/S0025.html", 1);
        protectedUrls.put("/S0025.jsp", 1);
        protectedUrls.put("/S0030.html", 2); // アカウント権限が必要（10）
        protectedUrls.put("/S0030.jsp", 2);
        protectedUrls.put("/S0031.html", 2);
        protectedUrls.put("/S0031.jsp", 2);
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        // 除外（静的ファイルなど）
        if (path.startsWith("/css/") || path.startsWith("/js/")) {
            chain.doFilter(request, response);
            return;
        }

//        System.out.println("[AuthorityFilter] URI: " + uri);
//        System.out.println("[AuthorityFilter] path: " + path);
//        System.out.println("[AuthorityFilter] is protected: " + protectedUrls.containsKey(path));
        // 対象ではなければ通過
        if (!protectedUrls.containsKey(path)) {
            chain.doFilter(request, response);
            return;
        }
        // 権限ビットをセッションから取得
        HttpSession session = request.getSession(false);// falseですでに存在するセッションのみ取得
        // セッションを取得
        // 存在する＞＞loginAutorityをIntegerにキャスト
        // 存在しない＞＞userAuthはnull
        Integer userAuth = (session != null) ? (Integer) session.getAttribute("loginAuthority") : null;

        // 
        if (userAuth == null) {
//            System.out.println("[AuthorityFilter] loginAuthority is null");
            response.sendRedirect(contextPath + "/C0010.html");
            return;
        }
        
        int required = protectedUrls.get(path); // ページに必要な権限を取得
//        System.out.println("[AuthorityFilter] userAuth: " + userAuth + ", required: " + required);

        if ((userAuth & required) == 0) {
            System.out.println("[AuthorityFilter] 権限が不足しています。");
            response.sendRedirect(contextPath + "/accessDenied.jsp");
            return;
        }

        // 通過OK
        chain.doFilter(request, response);
    }
}
