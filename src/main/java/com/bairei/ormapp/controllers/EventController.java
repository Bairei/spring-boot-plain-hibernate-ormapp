package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Event;
import com.bairei.ormapp.models.EventType;
import com.bairei.ormapp.services.BandService;
import com.bairei.ormapp.services.EventService;
import com.bairei.ormapp.repositories.PromoterRepository;
import com.bairei.ormapp.repositories.VenueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class EventController {

    private final EventService eventService;
    private final BandService bandService;
    private final VenueRepository venueRepository;
    private final PromoterRepository promoterRepository;

    public EventController (EventService eventService, BandService bandService, VenueRepository venueRepository,
                            PromoterRepository promoterRepository){
        this.eventService = eventService;
        this.bandService = bandService;
        this.venueRepository = venueRepository;
        this.promoterRepository = promoterRepository;
    }

    @GetMapping("/events")
    public String listEvents(Model model){
        model.addAttribute("events", eventService.findAll());
        return "events";
    }

    @GetMapping("/event/new")
    public String newEvent(Model model){
        model.addAttribute("event", new Event());
        model.addAttribute("bands", bandService.findAll());
        model.addAttribute("eventTypes", EventType.values());
        model.addAttribute("venues", venueRepository.findAll());
        model.addAttribute("promoters", promoterRepository.findAll());
        return "eventForm";
    }

    @GetMapping("/event/{id}/edit")
    public String editEvent(@PathVariable Long id, Model model){
        Event event = eventService.findById(id);
        if (event != null) {
            model.addAttribute("event", event);
            model.addAttribute("bands", bandService.findAll());
            model.addAttribute("eventTypes", EventType.values());
            model.addAttribute("venues", venueRepository.findAll());
            model.addAttribute("promoters", promoterRepository.findAll());
            return "eventForm";
        }
        return "redirect:/events";
    }

    @PostMapping("/event")
    public String postEvent(@ModelAttribute Event event, Model model){
        try {
            eventService.save(event);
        } catch (Exception e){
            log.warn(e.toString());
            model.addAttribute("event", event);
            model.addAttribute("bands", bandService.findAll());
            model.addAttribute("eventTypes", EventType.values());
            model.addAttribute("venues", venueRepository.findAll());
            model.addAttribute("promoters", promoterRepository.findAll());
            return "eventForm";
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

}
