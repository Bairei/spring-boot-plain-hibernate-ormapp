package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Genre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreToStringConverter implements Converter<Genre, String> {

    @Override
    public String convert(Genre genre) {
        return genre.getId().toString();
    }
}
