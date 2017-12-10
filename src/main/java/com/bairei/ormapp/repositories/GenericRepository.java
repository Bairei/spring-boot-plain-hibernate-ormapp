package com.bairei.ormapp.repositories;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T,ID extends Serializable> {
    void saveOrUpdate (T t);
    T save(T t);
    List<T> findAll();
    T findById(ID id);
    void delete(T t);
    void deleteById(ID id);
    Integer count();
}
