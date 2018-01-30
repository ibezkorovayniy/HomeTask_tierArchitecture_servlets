package com.MyApp.dbConnect;


import com.MyApp.controller.UserController;
import com.MyApp.dao.UserDao;
import com.MyApp.dao.UserDaoImpl;
import com.MyApp.service.UserService;
import com.MyApp.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

public class Factory {

public static final String URL = "jdbc:h2:tcp://localhost/~/test";
public static final String USER = "sa";
public static final String PASS = "";


    static {
        try {
            Class.forName("org.h2.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static UserDao getUserDao(Connection connection) {
        return new UserDaoImpl(connection);

    }

    public static UserService getUserService(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

    public static UserController getUserController(UserService userService) {
        return new UserController(userService);
    }

    public static <T, R> Function<T, R> getSomething(Function<T, R> f) {
        return f;
    }



}