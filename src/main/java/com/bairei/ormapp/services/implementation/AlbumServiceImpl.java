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
        album.setMembers(null);
        album.setGenre(null);
        album.setStudios(null);
        album.setLabel(null);
        albumRepository.save(album);
        albumRepository.deleteById(album.getId());
    }

    @Override
    public Integer count() {
        return albumRepository.count();
    }

    @Override
    public List<Album> findAlbumsByTitleIncluding(String title) {
        return albumRepository.findAlbumsByTitleIncluding(title);
    }
}
