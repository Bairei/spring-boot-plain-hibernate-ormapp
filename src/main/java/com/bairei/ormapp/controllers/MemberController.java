package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Member;
import com.bairei.ormapp.repositories.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    @GetMapping(value = "/members", produces = "application/json")
    public List<Member> memberList(){
        return memberRepository.listAll();
    }
}
