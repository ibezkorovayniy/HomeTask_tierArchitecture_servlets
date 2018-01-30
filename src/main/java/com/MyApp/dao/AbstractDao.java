package com.MyApp.dao;

import com.MyApp.annotations.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractDao<T> implements GenericDao<T> {

    Connection connection;

    AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T create(T t) {
        String sql =
                "INSERT INTO " +
                        Reflection.getTableName(t) + " (" +
                        Reflection.getKey(Reflection.getHashMap(t).keySet(), ",", "", "") + ") VALUES (" +
                        Reflection.getQuestions(Reflection.getHashMap(t).size()) +
                        ");";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            for (Map.Entry<Object, Object> entry : Reflection.getHashMap(t).entrySet()) {
                statement.setString(i, entry.getValue().toString());
                ++i;
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public T delete(T t) {
        try {
            String sql =
                    "DELETE FROM " + Reflection.getTableName(t) +
                            " WHERE " + Reflection.getKey(Reflection.getHashMap(t).keySet(), "=? AND ", "", "=?");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for (Map.Entry<Object, Object> entry : Reflection.getHashMap(t).entrySet()) {
                preparedStatement.setString(i, entry.getValue().toString());
                ++i;
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public T findById(Long id) {
        T t = null;
        Class<T> obj = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            t = obj.newInstance();
            String sql =
                    "SELECT * FROM " + Reflection.getTableName(t) +
                            " WHERE " + Reflection.getColumnNameId(t) +
                            "=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Reflection.getLoopOfResultSet(rs, t);

        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return t;
    }

    public T update(T t) {
        try {
            String sql =
                    "UPDATE " + Reflection.getTableName(t) +
                            " SET " + Reflection.getKey(Reflection.getHashMap(t).keySet(), "=?, ", "", "=?" +
                            " WHERE " + Reflection.getColumnNameId(t) + "=?");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Reflection.getLoopOfPreparedStatement(preparedStatement, t);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
}
