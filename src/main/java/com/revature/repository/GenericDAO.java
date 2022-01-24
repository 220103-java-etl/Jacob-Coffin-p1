package com.revature.repository;

import java.util.List;

public interface GenericDAO<T> {

    T add(T t);
    T getById(Integer id);
    T getByUsername(String username);
    List<T> getAll();
    void update(T t);
    void delete(Integer id);

}
