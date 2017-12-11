package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Promoter;
import com.bairei.ormapp.services.PromoterService;
import com.bairei.ormapp.utils.AjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@Slf4j
public class PromoterController {

    private final PromoterService promoterService;

    public PromoterController(PromoterService promoterService){
        this.promoterService = promoterService;
    }

    @GetMapping("/promoter/new")
    public String newPromoter(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newPromoter", new Promoter());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "promoterForm :: promoterForm";
        }
        return "redirect:/";
    }

    @GetMapping("/promoters")
    public String listPromoters(Model model){
        model.addAttribute("promoters", promoterService.findAll());
        return "promoters";
    }

    @PostMapping("/promoter/{id}/delete")
    public String deletePromoter(@PathVariable Long id){
        try {
            promoterService.deleteById(id);
        } catch (Exception e){
            log.warn(e.toString());
        }
        return "redirect:/promoters";
    }

}
