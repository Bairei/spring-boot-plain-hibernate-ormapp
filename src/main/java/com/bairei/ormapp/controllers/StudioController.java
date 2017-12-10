package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Studio;
import com.bairei.ormapp.repositories.LocationRepository;
import com.bairei.ormapp.repositories.StudioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class StudioController {

    private final StudioRepository studioRepository;
    private final LocationRepository locationRepository;

    public StudioController (StudioRepository studioRepository, LocationRepository locationRepository){
        this.studioRepository = studioRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/studio/new")
    public String newStudio(Model model){
        model.addAttribute("newStudio", new Studio());
        model.addAttribute("locations", locationRepository.findAll());
        return "studioform";
    }

    @PostMapping("/studio")
    public String postStudio(@ModelAttribute Studio studio, Model model){
        try {
            studioRepository.save(studio);
        } catch (Exception e){
            log.warn(e.toString());
            model.addAttribute("newStudio", studio);
            model.addAttribute("locations", locationRepository.findAll());
            return "studioform";
        }
        return "redirect:/studios";
    }

    @GetMapping("/studios")
    public String listStudios(Model model){
        model.addAttribute("studios", studioRepository.findAll());
        return "studios";
    }
}
