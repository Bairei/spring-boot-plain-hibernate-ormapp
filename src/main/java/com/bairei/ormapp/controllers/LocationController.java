package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Location;
import com.bairei.ormapp.utils.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class LocationController {

    @GetMapping("/location/new")
    public String newLocation(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newLocation", new Location());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "locationform :: locationForm";
        }
        return "redirect:/";
    }

}
