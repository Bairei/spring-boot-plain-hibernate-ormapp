package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.repositories.AlbumRepository;
import com.bairei.ormapp.services.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public void saveOrUpdate(Album album) {
        albumRepository.saveOrUpdate(album);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        Album album = albumRepository.findById(aLong);
        if (album != null) {
            album.setMembers(null);
            album.setGenre(null);
            album.setStudios(null);
            album.setLabel(null);
            albumRepository.save(album);
            albumRepository.deleteById(album.getId());
        }
    }

    @Override
    public Integer count() {
        return albumRepository.count();
    }

    @Override
    public List<Album> findAlbumsByMembersIncludingMemberNameEqualsIgnoreCase(String name) {
        return albumRepository.findAlbumsByMembersIncludingMemberNameEqualsIgnoreCase(name);
    }

    @Override
    public List<Album> findAlbumsByStudioNameEqualsIgnoreCase(String name) {
        return albumRepository.findAlbumsByStudioNameEqualsIgnoreCase(name);
    }

    @Override
    public List<Album> findAlbumsByLabelNameEqualsIgnoreCase(String name) {
        return albumRepository.findAlbumsByLabelNameEqualsIgnoreCase(name);
    }

    @Override
    public List<Album> findAlbumsByGenreNameEqualsIgnoreCase(String name) {
        return albumRepository.findAlbumsByGenreNameEqualsIgnoreCase(name);
    }
}
