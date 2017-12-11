package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StringToEventTypeConverter implements Converter<String, EventType> {
    @Override
    public EventType convert(String s) {
        log.info(s);
        return EventType.valueOf(s.toUpperCase());
    }
}
