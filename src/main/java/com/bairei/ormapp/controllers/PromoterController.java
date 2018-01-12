package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Promoter;
import com.bairei.ormapp.services.PromoterService;
import com.bairei.ormapp.utils.AjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return "staticpromoterform";
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


    @GetMapping("/promoter/{id}/edit")
    public String newPromoter(Model model, @PathVariable("id") Long id){
        Promoter promoter = promoterService.findById(id);
        if (promoter != null) {
            model.addAttribute("newPromoter", promoter);
            return "staticpromoterform";
        }
        return "redirect:/promoters";
    }

    @PostMapping("/promoter")
    public String postPromoter(@ModelAttribute("newPromoter") @Valid Promoter promoter, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("newPromoter", promoter);
            return "staticpromoterform";
        }
        try {
            promoterService.save(promoter);
            return "redirect:/promoters";
        } catch (Exception e){
            log.warn(e.getMessage());
            model.addAttribute("newPromoter", promoter);
            return "staticpromoterform";
        }
    }
}
