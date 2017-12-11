package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Promoter;
import com.bairei.ormapp.services.PromoterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToPromoterConverter implements Converter<String, Promoter> {

    private final PromoterService promoterService;

    public StringToPromoterConverter(PromoterService promoterService) {
        this.promoterService = promoterService;
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
        return promoterService.findById(id);
    }
}
