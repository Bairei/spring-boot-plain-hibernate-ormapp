package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.models.Genre;
import com.bairei.ormapp.repositories.AlbumRepository;
import com.bairei.ormapp.repositories.BandRepository;
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
    private final BandRepository bandRepository;
    private final AlbumRepository albumRepository;

    public GenreServiceImpl(GenreRepository genreRepository, BandRepository bandRepository, AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
        this.bandRepository = bandRepository;
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
        genreRepository.deleteById(aLong);
    }

    @Override
    public Integer count() {
        return genreRepository.count();
    }

    @Override
    public Band recommendABand(Long id) {
        Optional<Band> band = bandRepository.findAll().stream()
                .filter(b -> b.getGenre().getId().equals(id))
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected ->{
                    Collections.shuffle(collected);
                    return collected.stream();
                })).findFirst();
        return band.orElse(null);
    }
}
