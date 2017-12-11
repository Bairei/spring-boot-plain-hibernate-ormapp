package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.models.Genre;
import com.bairei.ormapp.repositories.*;
import com.bairei.ormapp.services.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Transactional
@Slf4j
@Controller
public class AlbumController {

    private final AlbumService albumService;
    private final MemberRepository memberRepository;
    private final LabelRepository labelRepository;
    private final GenreRepository genreRepository;
    private final BandRepository bandRepository;
    private final StudioRepository studioRepository;


    public AlbumController(AlbumService albumService, MemberRepository memberRepository, LabelRepository labelRepository,
                           GenreRepository genreRepository, BandRepository bandRepository, StudioRepository studioRepository){
        this.albumService = albumService;
        this.genreRepository = genreRepository;
        this.labelRepository = labelRepository;
        this.memberRepository = memberRepository;
        this.bandRepository = bandRepository;
        this.studioRepository = studioRepository;
    }

    @GetMapping(value = "/albums")
    public String albumList(Model model){
        model.addAttribute("albumList", albumService.findAll());
        return "albums";
    }

    @GetMapping("/album/search/{title}")
    public String albumListByTitle(@PathVariable String title, Model model){
        model.addAttribute(albumService.findAlbumsByTitleIncluding(title));
        return "albums";
    }

    @GetMapping("/album/new")
    public String newAlbumForm(Model model){
        formModel(model, new Album());
        return "albumform";
    }

    @GetMapping("/album/{id}/edit")
    public String editAlbum(@PathVariable Long id, Model model){
        Album album = albumService.findById(id);
        if (album != null){
            formModel(model, album);
            return "albumform";
        }
        return "redirect:/albums";
    }

    @PostMapping("/album")
    public String postAlbum(@ModelAttribute("album") @Valid Album album, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            formModel(model, album);
            return "albumform";
        }
        try {
            albumService.save(album);
        } catch (Exception e){
            log.warn(e.toString());
            formModel(model, album);
            return "albumform";
        }
        return "redirect:/albums";
    }

    @PostMapping("/album/{id}/delete")
    public String removeAlbum(@PathVariable Long id){
        try {
            albumService.deleteById(id);
        } catch (Exception e){
            log.warn(e.toString());
        }
        return "redirect:/albums";
    }

    private void formModel(Model model, Album album){
        model.addAttribute("album", album);
        model.addAttribute("bands", bandRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("allMembers", memberRepository.findAll());
        model.addAttribute("labels", labelRepository.findAll());
        model.addAttribute("studios", studioRepository.findAll());
    }
}
