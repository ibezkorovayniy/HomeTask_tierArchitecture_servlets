package com.MyApp.controller;

import com.MyApp.service.UserService;
import com.MyApp.util.Validator;
import com.MyApp.web.ViewModel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.MyApp.util.ApplicationConstants.TOKEN;
import static com.MyApp.web.Methods.GET;

public class LoginUserController implements Controller {

    private UserService userService;

    public LoginUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(HttpServletRequest request, HttpServletResponse responce) {
        ViewModel vm = new ViewModel("login");

        if (request.getMethod().equals(GET.toString())) {
            return vm;
        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Validator validator = new Validator(email, password);

        if (validator.getSuccessLogin()) {
            String token = userService.check(email, password).orElse("Unauthorised");
            responce.addCookie(new Cookie(TOKEN, token));
        }
        return vm;
    }
}