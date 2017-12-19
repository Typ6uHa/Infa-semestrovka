package filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    private final String COOKIE_USER = "user_id";
    private final String SESSION_USER = "current_user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                switch (cookie.getName()){
                    case COOKIE_USER:
                        request.getSession().setAttribute(SESSION_USER, cookie.getValue());
                        response.sendRedirect("/");
                        return;
                    default:
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}