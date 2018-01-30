package com.MyApp.annotations;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class Reflection {

    public static <T> String getTableName(T t) {
        Class<?> myClass = t.getClass();
        TableName tableName = myClass.getAnnotation(TableName.class);
        return tableName.value();
    }

    public static <T> Map<Object, Object> getHashMap(T t) {
        Class<?> myClass = t.getClass();
        Map<Object, Object> hashMap = new HashMap<>();
        for (Field field : myClass.getDeclaredFields()) {
            field.setAccessible(true);
            ColumnName columnName = field.getAnnotation(ColumnName.class);
            if (columnName != null) {
                try {
                    hashMap.put(columnName.value(), field.get(t).toString());
                } catch (IllegalAccessException e) {
                    System.out.println("IllegalAccessException");
                }
            }
        }
        return hashMap;
    }

    public static <T> String getColumnNameId(T t) {
        Class<?> myClass = t.getClass();
        for (Field field : myClass.getDeclaredFields()) {
            Id idColumn = field.getAnnotation(Id.class);
            if (idColumn != null) {
                return idColumn.value();
            }
        }
        return null;
    }

    public static <T> Long getIdValue(T t) {
        for (Field f : t.getClass().getDeclaredFields()) {
            Id id = f.getAnnotation(Id.class);
            if (id != null) {
                f.setAccessible(true);
                try {
                    return (Long) f.get(t);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static <T> String getImmutableColumn(T t) {
        Class<?> myClass = t.getClass();
        for (Field field : myClass.getDeclaredFields()) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                return field.getName();
            }
        }
        return null;
    }

    public static String getQuestions(int size) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (int i = 0; i < size; i++) {
            stringJoiner.add("?");
        }
        return stringJoiner.toString();
    }

    public static String getKey(Set set, String delimetr, String prefix, String suffix) {
        StringJoiner stringJoiner = new StringJoiner(delimetr, prefix, suffix);
        for (Object s : set) {
            stringJoiner.add(s.toString());
        }
        return stringJoiner.toString();
    }

    public static <T> void getLoopOfResultSet(ResultSet rs, T t) throws SQLException, IllegalAccessException {
        while (rs.next()) {
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                ColumnName columnName = field.getAnnotation(ColumnName.class);
                Id id = field.getAnnotation(Id.class);
                if (id != null) {
                    field.set(t, rs.getLong(id.value()));
                }
                if (columnName != null) {
                    switch (field.getType().getSimpleName()) {
                        case "String":
                            field.set(t, rs.getString(columnName.value()));
                            break;
                        case "Integer":
                            field.set(t, rs.getInt(columnName.value()));
                            break;
                        case "Long":
                            field.set(t, rs.getLong(columnName.value()));
                            break;
                        case "Date":
                            field.set(t, rs.getDate(columnName.value()));
                            break;
                        case "Double":
                            field.set(t, rs.getDouble(columnName.value()));
                            break;
                    }
                }
            }
        }
    }

    public static <T> void getLoopOfPreparedStatement(PreparedStatement preparedStatement, T t) throws SQLException {
        int i = 1;
        for (Map.Entry<Object, Object> entry : getHashMap(t).entrySet()) {
            switch (entry.getValue().getClass().getSimpleName()) {
                case "String":
                    preparedStatement.setString(i, (String) entry.getValue());
                    break;
                case "Integer":
                    preparedStatement.setInt(i, (Integer) entry.getValue());
                    break;
                case "Long":
                    preparedStatement.setLong(i, (Long) entry.getValue());
                    break;
                case "Date":
                    preparedStatement.setDate(i, (Date) entry.getValue());
                    break;
                case "Double":
                    preparedStatement.setDouble(i, (Double) entry.getValue());
                    break;
            }
            ++i;
        }
        preparedStatement.setLong(getHashMap(t).size() + 1, getIdValue(t));
    }
}
