package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Band;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BandToStringConverter implements Converter<Band, String> {
    @Override
    public String convert(Band band) {
        return band.toString();
    }
}
