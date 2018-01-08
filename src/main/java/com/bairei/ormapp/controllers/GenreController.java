package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Genre;
import com.bairei.ormapp.services.GenreService;
import com.bairei.ormapp.utils.AjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping("/genre/new")
    public String newGenre(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        model.addAttribute("newGenre", new Genre());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "genreform :: genreForm";
        }
        return "redirect:/";
    }

    @GetMapping("/genres")
    public String listGenres(Model model){
        model.addAttribute("genres", genreService.findAll());
        return "genres";
    }

    @PostMapping("/genre/{id}/delete")
    public String deleteGenre(@PathVariable Long id){
        try {
            genreService.deleteById(id);
        } catch (Exception e){
            log.warn(e.toString());
        }
        return "redirect:/genres";
    }

    @GetMapping("/genre/{id}/edit")
    public String newGenre(Model model, @PathVariable("id") Long id){
        Genre genre = genreService.findById(id);
        if (genre != null) {
            model.addAttribute("newGenre", genre);
            return "staticgenreform";
        }
        return "redirect:/genres";
    }

    @PostMapping("/genre")
    public String postGenre(@ModelAttribute("newGenre") @Valid Genre genre, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("newGenre", genre);
            return "staticgenreform";
        }
        try {
            genreService.save(genre);
            return "redirect:/genres";
        } catch (Exception e){
            log.warn(e.getMessage());
            model.addAttribute("newGenre", genre);
            return "staticgenreform";
        }
    }

// Not sure if it will be used later, but better keep it, just in case
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
