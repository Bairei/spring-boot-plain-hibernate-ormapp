package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Member;
import com.bairei.ormapp.services.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class StringToMemberConverter implements Converter<String, Member> {

    private final MemberService memberService;

    @Autowired
    public StringToMemberConverter(MemberService memberService){
        this.memberService = memberService;
    }

    @Override
    @Transactional
    public Member convert(String s) {
        Long id = -1L;
        try {
            id = Long.valueOf(s);
        } catch (NumberFormatException e){
            log.warn(e.toString());
            return null;
        }
        return memberService.findById(id);
    }
}
