package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Label;
import com.bairei.ormapp.repositories.LabelRepository;
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
public class LabelController {
    private final LabelRepository labelRepository;

    @Autowired
    public LabelController(LabelRepository labelRepository){
        this.labelRepository = labelRepository;
    }

    @GetMapping("/label/new")
    public String newLabel(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newLabel", new Label());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "labelform :: labelForm";
        }
        return "redirect:/";
    }

}
