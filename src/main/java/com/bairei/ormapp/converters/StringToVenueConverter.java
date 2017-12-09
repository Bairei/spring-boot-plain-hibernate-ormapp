package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Venue;
import com.bairei.ormapp.repositories.VenueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToVenueConverter implements Converter<String, Venue> {

    private final VenueRepository venueRepository;

    public StringToVenueConverter(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
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
        return venueRepository.findById(id);
    }
}
