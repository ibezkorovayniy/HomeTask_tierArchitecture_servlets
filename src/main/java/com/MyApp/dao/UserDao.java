package com.MyApp.dao;

import com.MyApp.model.User;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface UserDao extends GenericDao<User> {

    User getUserByEmail(String email);

    User getUserByToken(String token);

    User updateTokenByUser(User user);
}
