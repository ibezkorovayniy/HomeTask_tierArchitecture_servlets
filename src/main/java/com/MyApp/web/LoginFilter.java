package com.MyApp.web;

//import java.util.logging.Filter;

import com.MyApp.dao.UserDao;
import com.MyApp.dao.UserDaoImpl;
import com.MyApp.dbConnect.Factory;
import com.MyApp.model.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.MyApp.util.ApplicationConstants.TOKEN;

public class LoginFilter implements Filter {

    private UserDao userDao;
    private final String PROTECTED_URL = "/servlet/profile";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = new UserDaoImpl(Factory.getConnection());

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        if (request.getRequestURI().equals(PROTECTED_URL)) {
            String token = null;
            for (Cookie cookie : cookies) {
                String nameCookie = cookie.getName().toUpperCase();
                if (nameCookie.equals(TOKEN)) {
                    token = cookie.getValue();
                    User user = userDao.getUserByToken(token);
                    request.setAttribute("UserId", String.valueOf(user.getId()));
                }
            }
            if (token == null) {
                request.getRequestDispatcher("/servlet/login").forward(request, servletResponse);
            }
        }
        filterChain.doFilter(request,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
