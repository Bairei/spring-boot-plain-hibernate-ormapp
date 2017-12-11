package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Label;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LabelToStringConverter implements Converter<Label, String> {
    @Override
    public String convert(Label label) {
        return label.getId().toString();
    }
}
