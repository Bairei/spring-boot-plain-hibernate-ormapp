package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Venue;
import com.bairei.ormapp.services.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToVenueConverter implements Converter<String, Venue> {

    private final VenueService venueService;

    public StringToVenueConverter(VenueService venueService) {
        this.venueService = venueService;
    }

    @Override
    public Venue convert(String s) {
        Long id = -1L;
        try {
            id = Long.valueOf(s);
        } catch (NumberFormatException e){
            log.warn(e.toString());
            return null;
        }
        return venueService.findById(id);
    }
}
