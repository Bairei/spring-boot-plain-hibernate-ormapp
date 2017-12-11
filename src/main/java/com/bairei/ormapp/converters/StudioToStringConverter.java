package com.bairei.ormapp.converters;

        import com.bairei.ormapp.models.Studio;
        import org.springframework.core.convert.converter.Converter;
        import org.springframework.stereotype.Component;

@Component
public class StudioToStringConverter implements Converter<Studio, String> {
    @Override
    public String convert(Studio studio) {
        return studio.toString();
    }
}
