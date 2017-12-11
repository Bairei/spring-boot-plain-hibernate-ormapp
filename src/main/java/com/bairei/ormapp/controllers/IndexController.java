package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Event;
import com.bairei.ormapp.services.EventService;
import com.bairei.ormapp.services.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class IndexController {

    private final EventService eventService;
    private final GenreService genreService;

    @Autowired
    public IndexController (EventService eventService, GenreService genreService){
        this.eventService = eventService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Event> eventList = eventService.list5UpcomingEvents();
        log.info(eventList.toString());
        model.addAttribute("events", eventList);
        model.addAttribute("genres", genreService.findAll());
        return "index";
    }

}
