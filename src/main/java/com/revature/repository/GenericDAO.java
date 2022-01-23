package com.revature.repository;

import java.util.List;

public interface GenericDAO<T> {

    T add(T t);
    T getById(Integer id);
    T getByName(String first_name, String last_name);
    List<T> getAll();
    void update(T t);
    void delete(Integer id);

}
