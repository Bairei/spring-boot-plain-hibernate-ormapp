package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Event;
import com.bairei.ormapp.models.EventType;
import com.bairei.ormapp.services.BandService;
import com.bairei.ormapp.services.EventService;
import com.bairei.ormapp.services.PromoterService;
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
public class EventController {

    private final EventService eventService;
    private final BandService bandService;
    private final VenueService venueService;
    private final PromoterService promoterService;

    public EventController (EventService eventService, BandService bandService, VenueService venueService,
                            PromoterService promoterService){
        this.eventService = eventService;
        this.bandService = bandService;
        this.venueService = venueService;
        this.promoterService = promoterService;
    }

    @GetMapping("/events")
    public String listEvents(Model model){
        model.addAttribute("events", eventService.findAll());
        return "events";
    }

    @GetMapping("/event/new")
    public String newEvent(Model model){
        formModel(new Event(), model);
        return "eventform";
    }

    @GetMapping("/event/{id}/edit")
    public String editEvent(@PathVariable Long id, Model model){
        Event event = eventService.findById(id);
        if (event != null) {
            formModel(event, model);
            return "eventform";
        }
        return "redirect:/events";
    }

    @PostMapping("/event")
    public String postEvent(@ModelAttribute("event") @Valid Event event, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            formModel(event, model);
            return "eventform";
        }
        try {
            eventService.save(event);
        } catch (Exception e){
            log.warn(e.toString());
            formModel(event, model);
            return "eventform";
        }
        return "redirect:/events";
    }

    @PostMapping("/event/{id}/delete")
    public String removeBand(@PathVariable Long id){
        try {
            eventService.deleteById(id);
        } catch (Exception e){
            log.warn(e.toString());
        }
        return "redirect:/events";
    }

    private void formModel(Event event, Model model) {
        model.addAttribute("event", event);
        model.addAttribute("bands", bandService.findAll());
        model.addAttribute("eventTypes", EventType.values());
        model.addAttribute("venues", venueService.findAll());
        model.addAttribute("promoters", promoterService.findAll());
    }

}
