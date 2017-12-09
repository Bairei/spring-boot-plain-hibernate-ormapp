package com.bairei.ormapp.controllers;

import com.bairei.ormapp.repositories.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Transactional
@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @GetMapping("/members")
    public String memberList(Model model){
        model.addAttribute("members", memberRepository.listAll());
        return "members";
    }
}
