package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Label;
import com.bairei.ormapp.services.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToLabelConverter implements Converter<String, Label> {

    private final LabelService labelService;

    @Autowired
    public StringToLabelConverter (LabelService labelService){
        this.labelService = labelService;
    }

    @Override
    public Label convert(String s) {
        Long id = -1L;
        try {
            id = Long.valueOf(s);
        } catch (NumberFormatException e){
            log.warn(e.toString());
            return null;
        }
        return labelService.findById(id);
    }
}
