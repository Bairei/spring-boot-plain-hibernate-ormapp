package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Album;

import java.util.List;

public interface AlbumService extends GenericService<Album> {
    List<Album> findAlbumsByTitleIncluding(String title);
    List<Album> findAlbumsByMembersIncludingMemberNameEqualsIgnoreCase(String name);
    List<Album> findAlbumsByStudioNameEqualsIgnoreCase(String name);
}
