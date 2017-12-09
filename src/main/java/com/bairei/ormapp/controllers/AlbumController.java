package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.models.Genre;
import com.bairei.ormapp.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Transactional
@Slf4j
@Controller
public class AlbumController {

    private final AlbumRepository albumRepository;
    private final MemberRepository memberRepository;
    private final LabelRepository labelRepository;
    private final GenreRepository genreRepository;
    private final BandRepository bandRepository;


    @Autowired
    public AlbumController(AlbumRepository albumRepository, MemberRepository memberRepository, LabelRepository labelRepository,
                           GenreRepository genreRepository, BandRepository bandRepository){
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        this.labelRepository = labelRepository;
        this.memberRepository = memberRepository;
        this.bandRepository = bandRepository;
    }

    @GetMapping(value = "/albums")
    public String albumList(Model model){
        model.addAttribute("albumList", albumRepository.listAll());
        return "albums";
    }

    @GetMapping("/album/search/{title}")
    public String albumListByTitle(@PathVariable String title, Model model){
        model.addAttribute(albumRepository.findAlbumsByTitleIncluding(title));
        return "albums";
    }

    @GetMapping("/album/new")
    public String newAlbumForm(Model model){
        model.addAttribute("album", new Album());
        model.addAttribute("bands", bandRepository.listAll());
        model.addAttribute("genres", genreRepository.listAll());
        model.addAttribute("allMembers", memberRepository.listAll());
        model.addAttribute("labels", labelRepository.listAll());
        model.addAttribute("newGenre", new Genre());
        return "albumform";
    }

    @PostMapping("/album")
    public String postAlbum(@ModelAttribute Album album, Model model){
        try {
            albumRepository.save(album);
        } catch (Exception e){
            log.warn(e.toString());
            model.addAttribute("album", album);
            model.addAttribute("bands", bandRepository.listAll());
            model.addAttribute("genres", genreRepository.listAll());
            model.addAttribute("allMembers", memberRepository.listAll());
            model.addAttribute("labels", labelRepository.listAll());
            return "albumform";
        }
        return "redirect:/albums";
    }
}
