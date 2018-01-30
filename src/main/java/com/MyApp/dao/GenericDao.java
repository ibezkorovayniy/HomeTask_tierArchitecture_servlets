package com.MyApp.dao;

public interface GenericDao<T> {

    T create (T t);

    T findById(Long id);

    T update(T t);

    T delete(T t);


}