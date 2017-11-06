package com.bairei.ormapp.controllers;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.repositories.BandRepository;
import com.bairei.ormapp.services.BandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BandController {

    private BandService bandService;

    private final static Logger log = LoggerFactory.getLogger(BandController.class);

    public BandController(BandService bandService){
        this.bandService = bandService;
    }

    @Transactional
    @GetMapping(value = "/bands", produces = "application/json")
    public List<Band> bandList(){
        log.info(bandService.count().toString());
        return bandService.findAll();
    }

}
