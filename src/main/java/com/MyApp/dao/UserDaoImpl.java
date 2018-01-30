package com.MyApp.dao;

import com.MyApp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public User create(User user) {
        return super.create(user);
    }

    public User findById(Long id) {
        return super.findById(id);
    }

    public User update(User user) {
        return super.update(user);
    }

    public User delete(User user) {
        return super.delete(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            String sql = "SELECT * FROM USERS WHERE EMAIL=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            user = setUserParamentr(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByToken(String token) {
        User user = null;
        try {
            String sql = "SELECT * FROM USERS WHERE TOKEN=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, token);
            ResultSet rs = preparedStatement.executeQuery();
            user = setUserParamentr(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User updateTokenByUser(User user) {
        try {
            String sql = "UPDATE USERS SET TOKEN=? WHERE ID=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getToken());
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeUpdate();
            //user = setUserParamentr(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User setUserParamentr(ResultSet rs) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        User user = new User();
        if (rs.next()) {
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setToken(rs.getString("token"));
        }
        return user;
    }


}