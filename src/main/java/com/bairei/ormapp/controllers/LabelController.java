package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Label;
import com.bairei.ormapp.services.LabelService;
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
public class LabelController {
    private final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService){
        this.labelService = labelService;
    }

    @GetMapping("/label/new")
    public String newLabel(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newLabel", new Label());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "labelform :: labelForm";
        }
        return "redirect:/";
    }

    @GetMapping("/labels")
    public String listLabels(Model model){
        model.addAttribute("labels", labelService.findAll());
        return "labels";
    }

    @PostMapping("/label/{id}/delete")
    public String deleteLabel(@PathVariable Long id){
        try {
            labelService.deleteById(id);
        } catch (Exception e){
            log.warn(e.toString());
        }
        return "redirect:/labels";
    }
}
