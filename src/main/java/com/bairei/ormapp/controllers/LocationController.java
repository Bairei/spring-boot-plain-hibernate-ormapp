package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Location;
import com.bairei.ormapp.services.LocationService;
import com.bairei.ormapp.utils.AjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @GetMapping("/location/new")
    public String newLocation(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newLocation", new Location());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "locationform :: locationForm";
        }
        return "redirect:/";
    }

    @GetMapping("/locations")
    public String listLocations(Model model){
        model.addAttribute("locations", locationService.findAll());
        return "locations";
    }

    @PostMapping("/location/{id}/delete")
    public String deleteLocation(@PathVariable Long id){
        try {
            locationService.deleteById(id);
        } catch (Exception e){
            log.warn(e.toString());
        }
        return "redirect:/locations";
    }

    @GetMapping("/location/{id}/edit")
    public String newLocation(Model model, @PathVariable("id") Long id){
        Location location = locationService.findById(id);
        if (location != null) {
            model.addAttribute("newLocation", locationService.findById(id));
            return "staticlocationform";
        }
        return "redirect:/locations";
    }

    @PostMapping("/location")
    public String postLocation(@ModelAttribute("newLocation") @Valid Location location, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("newLocation", location);
            return "staticlocationform";
        }
        try {
            locationService.save(location);
            return "redirect:/locations";
        } catch (Exception e){
            log.warn(e.getMessage());
            model.addAttribute("newLocation", location);
            return "staticlocationform";
        }
    }
}
