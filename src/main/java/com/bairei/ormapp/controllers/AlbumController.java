package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.repositories.AlbumRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumController {

    private AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    @Transactional
    @GetMapping(value = "/albums", produces = "application/json")
    public List<Album> albumList(){
        return albumRepository.listAll();
    }

    @Transactional
    @GetMapping(value = "/album/search/{title}", produces = "application/json")
    public List<Album> albumListByTitle(@PathVariable String title){
        return albumRepository.findAlbumsByTitleIncluding(title);
    }

}
