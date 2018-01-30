package com.MyApp.controller;

import com.MyApp.model.User;
import com.MyApp.service.UserService;
import com.MyApp.util.Validator;
import com.MyApp.web.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.MyApp.web.Methods.GET;
import static com.MyApp.web.Methods.POST;

public class CreateUserController implements Controller {
    private UserService service;

    public CreateUserController(UserService userService) {
        this.service = userService;
    }


    @Override
    public ViewModel process(HttpServletRequest request, HttpServletResponse response) {
        ViewModel vm = new ViewModel("register");

        if (request.getMethod().equals(GET.toString())) {
            return new ViewModel("register");
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re-password");

        Validator validator = new Validator(name, email, password, rePassword);

        if (validator.getValidateAll()) {
            service.create(new User(name, email, password));
        }
        return vm;
    }
}
