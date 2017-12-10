package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Album;

import java.util.List;

public interface AlbumService {
    Album save(Album album);
    void saveOrUpdate(Album album);
    List<Album> findAll();
    Album findById(Long id);
    void deleteById(Long aLong);
    Integer count();
    List<Album> findAlbumsByTitleIncluding(String title);
}
