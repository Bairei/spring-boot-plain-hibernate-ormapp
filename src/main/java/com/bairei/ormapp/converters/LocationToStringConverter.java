package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Location;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocationToStringConverter implements Converter<Location, String>{
    @Override
    public String convert(Location location) {
        return location.toString();
    }
}
