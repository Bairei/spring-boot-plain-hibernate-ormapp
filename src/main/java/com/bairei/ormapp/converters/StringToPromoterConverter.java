package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Promoter;
import com.bairei.ormapp.repositories.PromoterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToPromoterConverter implements Converter<String, Promoter> {

    private final PromoterRepository promoterRepository;

    public StringToPromoterConverter(PromoterRepository promoterRepository) {
        this.promoterRepository = promoterRepository;
    }

    @Override
    public Promoter convert(String s) {
        Long id = -1L;
        try {
            id = Long.valueOf(s);
        } catch (NumberFormatException e){
            log.warn(e.toString());
            return null;
        }
        return promoterRepository.findById(id);
    }
}
