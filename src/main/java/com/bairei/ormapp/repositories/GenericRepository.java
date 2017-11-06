package com.bairei.ormapp.repositories;

import java.util.List;

public interface GenericRepository<T,ID> {
    void saveOrUpdate (T t);
    T save(T t);
    List<T> listAll();
    T findById(ID id);
    void delete(T t);
    void deleteById(ID id);
    Integer count();
}
