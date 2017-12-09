package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.repositories.GenreRepository;
import com.bairei.ormapp.repositories.MemberRepository;
import com.bairei.ormapp.services.BandService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        log.info(bandService.count().toString());
        model.addAttribute("bands", bandService.findAll());
        return "bands";
    }

    @GetMapping("/band/search/{name}")
    public String bandListByName(@PathVariable String name, Model model){
        model.addAttribute("bands", bandService.findBandsByNameIncluding(name));
        return "bands";
    }

    @PostMapping("/band")
    public String postBand(@ModelAttribute Band band){
        try {
            bandService.save(band);
        } catch (Exception e){
            log.warn(e.toString());
            return "bandsform";
        }
        return "redirect:/bands/";
    }

    @GetMapping("/band/{id}/edit")
    public String editBand (@PathVariable Long id, Model model){
        Band band = bandService.findById(id);
        if (band != null){
            model.addAttribute("band", band);
            model.addAttribute("genres", genreRepository.listAll());
            model.addAttribute("allMembers", memberRepository.listAll());
            return "bandsform";
        }
        return "redirect:/bands";
    }


    @GetMapping("/band/new")
    public String newBand(Model model){
        model.addAttribute("band", new Band());
        model.addAttribute("genres", genreRepository.listAll());
        model.addAttribute("allMembers", memberRepository.listAll());
        return "bandsform";
    }
}
