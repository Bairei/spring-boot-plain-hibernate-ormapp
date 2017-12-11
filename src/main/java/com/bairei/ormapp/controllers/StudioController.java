package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Studio;
import com.bairei.ormapp.repositories.LocationRepository;
import com.bairei.ormapp.repositories.StudioRepository;
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
public class StudioController {

    private final StudioRepository studioRepository;
    private final LocationRepository locationRepository;

    public StudioController (StudioRepository studioRepository, LocationRepository locationRepository){
        this.studioRepository = studioRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/studio/new")
    public String newStudio(Model model){
        formModel(new Studio(), model);
        return "studioform";
    }

    @PostMapping("/studio")
    public String postStudio(@ModelAttribute("studio") @Valid Studio studio, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            formModel(studio, model);
            return "studioform";
        }
        try {
            studioRepository.save(studio);
        } catch (Exception e){
            log.warn(e.toString());
            formModel(studio, model);
            return "studioform";
        }
        return "redirect:/studios";
    }


    @GetMapping("/studios")
    public String listStudios(Model model){
        model.addAttribute("studios", studioRepository.findAll());
        return "studios";
    }

    @GetMapping("/studio/{id}/edit")
    public String editStudio(@PathVariable Long id, Model model){
        Studio studio = studioRepository.findById(id);
        if (studio != null){
            formModel(studio, model);
            return "studioform";
        }
        return "redirect:/studios";
    }

    private void formModel(Studio studio, Model model) {
        model.addAttribute("newStudio", studio);
        model.addAttribute("locations", locationRepository.findAll());
    }
}
