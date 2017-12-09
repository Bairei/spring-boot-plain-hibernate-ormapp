package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Genre;
import com.bairei.ormapp.repositories.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class StringToGenreConverter implements Converter<String, Genre> {

    private final GenreRepository genreRepository;

    @Autowired
    public StringToGenreConverter(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public Genre convert(String s) {
        Long id = -1L;
        try {
            id = Long.valueOf(s);
        } catch (NumberFormatException e){
            log.warn(e.toString());
            return null;
        }
        return genreRepository.findById(id);
    }
}
