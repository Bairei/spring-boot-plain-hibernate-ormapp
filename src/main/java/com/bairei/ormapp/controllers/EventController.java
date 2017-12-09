package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Event;
import com.bairei.ormapp.models.EventType;
import com.bairei.ormapp.repositories.BandRepository;
import com.bairei.ormapp.repositories.EventRepository;
import com.bairei.ormapp.repositories.PromoterRepository;
import com.bairei.ormapp.repositories.VenueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class EventController {

    private final EventRepository eventRepository;
    private final BandRepository bandRepository;
    private final VenueRepository venueRepository;
    private final PromoterRepository promoterRepository;

    public EventController (EventRepository eventRepository, BandRepository bandRepository, VenueRepository venueRepository,
                            PromoterRepository promoterRepository){
        this.eventRepository = eventRepository;
        this.bandRepository = bandRepository;
        this.venueRepository = venueRepository;
        this.promoterRepository = promoterRepository;
    }

    @GetMapping("/events")
    public String listEvents(Model model){
        model.addAttribute("events", eventRepository.listAll());
        return "events";
    }

    @GetMapping("/event/new")
    public String newEvent(Model model){
        model.addAttribute("event", new Event());
        model.addAttribute("bands", bandRepository.listAll());
        model.addAttribute("eventTypes", EventType.values());
        model.addAttribute("venues", venueRepository.listAll());
        model.addAttribute("promoters", promoterRepository.listAll());
        return "eventForm";
    }

    @PostMapping("/event")
    public String postEvent(@ModelAttribute Event event, Model model){
        try {
            eventRepository.save(event);
        } catch (Exception e){
            log.warn(e.toString());
            model.addAttribute("event", event);
            model.addAttribute("bands", bandRepository.listAll());
            model.addAttribute("eventTypes", EventType.values());
            model.addAttribute("venues", venueRepository.listAll());
            model.addAttribute("promoters", promoterRepository.listAll());
            return "eventForm";
        }
        return "redirect:/events";
    }

}
