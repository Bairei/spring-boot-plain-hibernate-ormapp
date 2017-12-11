package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.repositories.GenreRepository;
import com.bairei.ormapp.repositories.MemberRepository;
import com.bairei.ormapp.services.BandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Transactional
@Controller
@Slf4j
public class BandController {

    private final BandService bandService;
    private final MemberRepository memberRepository;
    private final GenreRepository genreRepository;

    public BandController(BandService bandService, GenreRepository genreRepository, MemberRepository memberRepository){
        this.bandService = bandService;
        this.memberRepository = memberRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/bands")
    public String bandList(Model model){
        model.addAttribute("bands", bandService.findAll());
        return "bands";
    }

    @GetMapping("/band/search/{name}")
    public String bandListByName(@PathVariable String name, Model model){
        model.addAttribute("bands", bandService.findBandsByNameIncluding(name));
        return "bands";
    }

    @PostMapping("/band")
    public String postBand(@ModelAttribute("band") @Valid Band band, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            formModel(band, model);
            return "bandsform";
        }
        try {
            bandService.save(band);
        } catch (Exception e){
            log.warn(e.toString());
            formModel(band, model);
            return "bandsform";
        }
        return "redirect:/bands/";
    }

    @GetMapping("/band/{id}/edit")
    public String editBand (@PathVariable Long id, Model model){
        Band band = bandService.findById(id);
        if (band != null){
            formModel(band, model);
            return "bandsform";
        }
        return "redirect:/bands";
    }

    @GetMapping("/band/new")
    public String newBand(Model model){
        formModel(new Band(), model);
        return "bandsform";
    }

    @PostMapping("/band/{id}/delete")
    public String removeBand(@PathVariable Long id){
        try {
            bandService.deleteById(id);
        } catch (Exception e){
            log.warn(e.toString());
        }
        return "redirect:/bands";
    }

    private void formModel(Band band, Model model) {
        model.addAttribute("band", band);
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("allMembers", memberRepository.findAll());
    }

}
