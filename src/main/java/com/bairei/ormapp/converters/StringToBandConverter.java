package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.services.BandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToBandConverter implements Converter<String, Band> {

    private final BandService bandService;

    @Autowired
    public StringToBandConverter(BandService bandService){
        this.bandService = bandService;
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
        return bandService.findById(id);
    }
}
