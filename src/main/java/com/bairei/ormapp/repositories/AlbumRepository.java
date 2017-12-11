package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.models.Member;

import java.util.List;

public interface AlbumRepository extends GenericRepository<Album, Long> {
    List<Album> findAlbumsByTitleIncluding(String title);

    List<Album> findAlbumsByGenreNameEqualsIgnoreCase(String genreName);

    List<Album> findAlbumsByMembersIncludingMemberNameEqualsIgnoreCase(String name);

    List<Album> findAlbumsByStudioNameEqualsIgnoreCase(String name);

    List<Album> findAlbumsByLabelNameEqualsIgnoreCase(String name);
}
