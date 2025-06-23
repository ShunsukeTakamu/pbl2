package filter;

import java.io.IOException;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "*.jsp" }, dispatcherTypes = { DispatcherType.REQUEST })
public class JspFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (req.getDispatcherType() == DispatcherType.REQUEST) {
			String uri = req.getRequestURI();

			// リダイレクト先の accessDenied.jsp は除外
			if (uri.endsWith("accessDenied.jsp")) {
				chain.doFilter(request, response);
				return;
			}

			String contextPath = req.getContextPath();
			res.sendRedirect(contextPath + "/accessDenied.jsp");
			return;
		}

		// FORWARDなどは通す
		chain.doFilter(request, response);
	}
}
