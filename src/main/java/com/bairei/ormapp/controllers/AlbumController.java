package com.bairei.ormapp.controllers;

import com.bairei.ormapp.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Transactional
@Controller
public class AlbumController {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumController(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
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
}
