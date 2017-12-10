package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.models.Genre;

import java.util.List;

public interface GenreService {
    Genre save(Genre genre);
    void saveOrUpdate(Genre genre);
    List<Genre> findAll();
    Genre findById(Long id);
    void deleteById(Long aLong);
    Integer count();
    Band recommendABand(Long id);
}
