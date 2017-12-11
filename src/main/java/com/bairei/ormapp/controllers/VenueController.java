package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Venue;
import com.bairei.ormapp.repositories.LocationRepository;
import com.bairei.ormapp.repositories.VenueRepository;
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

    private final VenueRepository venueRepository;
    private final LocationRepository locationRepository;

    public VenueController(VenueRepository venueRepository, LocationRepository locationRepository){
        this.venueRepository = venueRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/venues")
    public String listVenues(Model model){
        model.addAttribute("venues", venueRepository.findAll());
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
            venueRepository.save(venue);
        } catch (Exception e){
            log.warn(e.toString());
            formModel(venue, model);
            return "venueform";
        }
        return "redirect:/venues";
    }


    @GetMapping("/venue/{id}/edit")
    public String editVenue (@PathVariable Long id, Model model){
        Venue venue = venueRepository.findById(id);
        if (venue != null){
            formModel(venue, model);
            return "venueform";
        }
        return "redirect:/venues";
    }

    private void formModel(Venue venue, Model model) {
        model.addAttribute("venue", venue);
        model.addAttribute("locations", locationRepository.findAll());
    }
}
