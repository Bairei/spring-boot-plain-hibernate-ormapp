package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.repositories.BandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToBandConverter implements Converter<String, Band> {

    private final BandRepository bandRepository;

    @Autowired
    public StringToBandConverter(BandRepository bandRepository){
        this.bandRepository = bandRepository;
    }

    @Override
    public Band convert(String s) {
        Long id = -1L;
        try {
            id = Long.valueOf(s);
        } catch (NumberFormatException e){
            log.warn(e.toString());
            return null;
        }
        return bandRepository.findById(id);
    }
}
