package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Studio;
import com.bairei.ormapp.services.StudioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToStudioConverter implements Converter<String, Studio> {

    private final StudioService studioService;

    public StringToStudioConverter(StudioService studioService){
        this.studioService = studioService;
    }

    @Override
    public Studio convert(String s) {
        Long id = -1L;
        try {
            id = Long.valueOf(s);
        } catch (NumberFormatException e){
            log.warn(e.toString());
            return null;
        }
        return studioService.findById(id);
    }
}
