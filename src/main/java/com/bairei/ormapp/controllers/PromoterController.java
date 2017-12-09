package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Promoter;
import com.bairei.ormapp.repositories.PromoterRepository;
import com.bairei.ormapp.utils.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class PromoterController {

    private final PromoterRepository promoterRepository;

    public PromoterController(PromoterRepository promoterRepository){
        this.promoterRepository = promoterRepository;
    }

    @GetMapping("/promoter/new")
    public String newPromoter(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newPromoter", new Promoter());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "promoterForm :: promoterForm";
        }
        return "redirect:/";
    }

}
