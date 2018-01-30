package com.MyApp.service;

import com.MyApp.model.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    User findById(Long id);

    User update(User user);

    User delete(User user);

    Optional <String>check(String email,String password);

    User getUserByToken(String token);
}
