package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Album;

import java.util.List;

public interface AlbumRepository extends GenericRepository<Album, Long> {
    List<Album> findAlbumsByTitleIncluding(String title);
}
