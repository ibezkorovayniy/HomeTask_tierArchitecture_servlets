package com.MyApp.controller;

import com.MyApp.model.User;
import com.MyApp.service.UserService;
import com.MyApp.web.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController implements Controller {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User create(User user) {
        return userService.create(user);
    }

    public User findById(Long id) {
        return userService.findById(id);
    }

    public User update(User user) {
        return userService.update(user);
    }

    public User delete(User user) {
        return userService.delete(user);
    }

    @Override
    public ViewModel process(HttpServletRequest request, HttpServletResponse responce) {
        return null;
    }
}