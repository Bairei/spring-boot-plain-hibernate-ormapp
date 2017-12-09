package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Member;
import com.bairei.ormapp.repositories.MemberRepository;
import com.bairei.ormapp.utils.AjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members")
    public String memberList(Model model){
        model.addAttribute("members", memberRepository.listAll());
        return "members";
    }

    @GetMapping("/member/new")
    public String newMember(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newMember", new Member());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "memberform :: memberForm";
        }
        return "memberform";
    }

    @PostMapping("/member")
    public String postMember(@ModelAttribute Member member, Model model, HttpServletRequest request){
        String[] header = request.getHeader("Referer").split("/");
        try {
            memberRepository.save(member);
        } catch (Exception e){
            log.warn(e.toString());
            model.addAttribute("newMember", member);
            return "memberform :: memberForm";
        }
        if (("/" + header[3] + "/" + header[4]).equalsIgnoreCase("/member/new")) return "redirect:/";
        return "redirect:/" + header[3] + "/" + header[4];
    }
}
