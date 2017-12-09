package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.EventType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEventTypeConverter implements Converter<String, EventType> {
    @Override
    public EventType convert(String s) {
        return EventType.valueOf(s);
    }
}
