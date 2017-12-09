package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Label;
import com.bairei.ormapp.repositories.LabelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToLabelConverter implements Converter<String, Label> {

    private final LabelRepository labelRepository;

    @Autowired
    public StringToLabelConverter (LabelRepository labelRepository){
        this.labelRepository = labelRepository;
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
        return labelRepository.findById(id);
    }
}
