package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Album;

import java.util.List;

public interface AlbumService extends GenericService<Album> {
    List<Album> findAlbumsByMembersIncludingMemberNameEqualsIgnoreCase(String name);
    List<Album> findAlbumsByStudioNameEqualsIgnoreCase(String name);
    List<Album> findAlbumsByLabelNameEqualsIgnoreCase(String name);
    List<Album> findAlbumsByGenreNameEqualsIgnoreCase(String name);
}
