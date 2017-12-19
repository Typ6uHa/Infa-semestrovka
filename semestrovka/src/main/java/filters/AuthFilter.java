package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) servletRequest;
        if (r.getSession().getAttribute("current_user") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            ((HttpServletResponse)servletResponse).sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
