package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.models.Genre;
import com.bairei.ormapp.services.AlbumService;
import com.bairei.ormapp.services.BandService;
import com.bairei.ormapp.repositories.GenreRepository;
import com.bairei.ormapp.services.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BandService bandService;
    private final AlbumService albumService;

    public GenreServiceImpl(GenreRepository genreRepository, BandService bandService, AlbumService albumService){
        this.albumService = albumService;
        this.bandService = bandService;
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void saveOrUpdate(Genre genre) {
        genreRepository.saveOrUpdate(genre);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        Genre genre = genreRepository.findById(aLong);
        if (genre != null){
            List<Album> albums = albumService.findAlbumsByGenreNameEqualsIgnoreCase(genre.getName());
            for (Album album: albums){
                albumService.deleteById(album.getId());
            }
            List<Band> bands = bandService.findBandsByGenreNameEqualsIgnoreCase(genre.getName());
            for (Band band: bands){
                bandService.deleteById(band.getId());
            }
            genreRepository.deleteById(aLong);
        }
    }

    @Override
    public Integer count() {
        return genreRepository.count();
    }

    @Override
    public Band recommendABand(Long id) {
        Optional<Band> band = bandService.findAll().stream()
                .filter(b -> b.getGenre().getId().equals(id))
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected ->{
                    Collections.shuffle(collected);
                    return collected.stream();
                })).findFirst();
        return band.orElse(null);
    }
}
