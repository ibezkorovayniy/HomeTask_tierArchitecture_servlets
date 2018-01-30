package com.MyApp.service;

import com.MyApp.dao.UserDao;
import com.MyApp.model.User;
import com.MyApp.util.BaseUtil;

import java.time.LocalTime;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User create(User user) {
        return userDao.create(user);
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public User update(User user) {
        return userDao.update(user);
    }

    public User delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public Optional<String> check(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            String token = BaseUtil.getUserToken();
            user.setToken(token);
            userDao.updateTokenByUser(user);
            return Optional.of(token);
        }
        return Optional.empty();
    }

    @Override
    public User getUserByToken(String token) {
        return userDao.getUserByToken(token);
    }


}