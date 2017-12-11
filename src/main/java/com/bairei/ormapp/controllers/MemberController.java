package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Member;
import com.bairei.ormapp.repositories.MemberRepository;
import com.bairei.ormapp.services.MemberService;
import com.bairei.ormapp.utils.AjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public String memberList(Model model){
        model.addAttribute("members", memberService.findAll());
        return "members";
    }

    @GetMapping("/member/new")
    public String newMember(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newMember", new Member());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "memberform :: memberForm";
        }
        return "redirect:/members";
    }

    @PostMapping("/member/{id}/delete")
    public String deleteMember(@PathVariable Long id){
        try {
            memberService.deleteById(id);
        } catch (Exception e){
            log.warn(e.toString());
        }
        return "redirect:/members";
    }
}
