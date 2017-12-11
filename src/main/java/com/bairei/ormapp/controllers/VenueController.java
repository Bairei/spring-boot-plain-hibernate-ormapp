package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Venue;
import com.bairei.ormapp.services.LocationService;
import com.bairei.ormapp.services.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class VenueController {

    private final VenueService venueService;
    private final LocationService locationService;

    public VenueController(VenueService venueService, LocationService locationService){
        this.venueService = venueService;
        this.locationService = locationService;
    }

    @GetMapping("/venues")
    public String listVenues(Model model){
        model.addAttribute("venues", venueService.findAll());
        return "venues";
    }

    @GetMapping("/venue/new")
    public String newVenue(Model model){
        formModel(new Venue(), model);
        return "venueform";
    }

    @PostMapping("/venue")
    public String postVenue(@ModelAttribute("venue") @Valid Venue venue, BindingResult result, Model model){
        if(result.hasErrors()){
            formModel(venue, model);
            return "venueform";
        }
        try{
            venueService.save(venue);
        } catch (Exception e){
            log.warn(e.toString());
            formModel(venue, model);
            return "venueform";
        }
        return "redirect:/venues";
    }


    @GetMapping("/venue/{id}/edit")
    public String editVenue (@PathVariable Long id, Model model){
        Venue venue = venueService.findById(id);
        if (venue != null){
            formModel(venue, model);
            return "venueform";
        }
        return "redirect:/venues";
    }

    @PostMapping("/venue/{id}/delete")
    public String deleteVenue(@PathVariable Long id){
        try {
            venueService.deleteById(id);
        } catch (Exception e){
            log.warn(e.toString());
        }
        return "redirect:/venues";
    }

    private void formModel(Venue venue, Model model) {
        model.addAttribute("venue", venue);
        model.addAttribute("locations", locationService.findAll());
    }
}
