package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Genre;
import com.bairei.ormapp.repositories.GenreRepository;
import com.bairei.ormapp.utils.AjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class GenreController {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @GetMapping("/genre/new")
    public String newGenre(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newGenre", new Genre());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "genreform :: genreForm";
        }
        return "redirect:/";
    }
// Not sure if it will be used later, but better to keep it, just in case
//    @PostMapping("/genre")
//    public String postGenre(@ModelAttribute Genre genre, Model model, HttpServletRequest request){
//        String[] header = request.getHeader("Referer").split("/");
//        try {
//            genreRepository.save(genre);
//        } catch (Exception e){
//            log.warn(e.toString());
//            model.addAttribute("newGenre", genre);
//            return "genreform :: genreForm";
//        }
//        if (("/" + header[3] + "/" + header[4]).equalsIgnoreCase("/genre/new")) return "redirect:/";
//        return "redirect:/" + header[3] + "/" + header[4];
//    }
}
