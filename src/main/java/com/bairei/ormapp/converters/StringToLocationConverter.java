package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Location;
import com.bairei.ormapp.services.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToLocationConverter implements Converter<String, Location> {

    private final LocationService locationService;

    public StringToLocationConverter(LocationService locationService){
        this.locationService = locationService;
    }

    @Override
    public Location convert(String s) {
        Long id = -1L;
        try {
            id = Long.valueOf(s);
        } catch (NumberFormatException e){
            log.warn(e.toString());
            return null;
        }
        return locationService.findById(id);
    }
}
