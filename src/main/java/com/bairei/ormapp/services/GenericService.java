package com.bairei.ormapp.services;

import java.util.List;

public interface GenericService<T> {
    T save(T t);
    void saveOrUpdate(T t);
    List<T> findAll();
    T findById(Long id);
    void deleteById(Long aLong);
    Integer count();
}
