package com.bairei.ormapp.converters;

import com.bairei.ormapp.models.Member;
import com.bairei.ormapp.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class StringToMemberConverter implements Converter<String, Member> {

    private final MemberRepository memberRepository;

    @Autowired
    public StringToMemberConverter(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
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
        return memberRepository.findById(id);
    }
}
