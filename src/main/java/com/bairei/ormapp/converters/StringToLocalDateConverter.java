package com.bairei.ormapp.converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@Slf4j
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            return LocalDate.parse(s, formatter);
        } catch (DateTimeParseException e){
            formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
            return LocalDate.parse(s, formatter);
        }
    }
}
