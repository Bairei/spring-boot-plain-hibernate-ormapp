package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Member;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MemberToStringConverter implements Converter<Member, String> {
    @Override
    public String convert(Member member) {
        return member.toString();
    }
}
