package com.bairei.ormapp.controllers.api;

import com.bairei.ormapp.models.*;
import com.bairei.ormapp.repositories.*;
import com.bairei.ormapp.services.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ApiController {

    private final LabelRepository labelRepository;
    private final MemberRepository memberRepository;
    private final GenreService genreService;
    private final LocationRepository locationRepository;
    private final PromoterRepository promoterRepository;

    @Autowired
    public ApiController(LabelRepository labelRepository, MemberRepository memberRepository, GenreService genreService,
                         LocationRepository locationRepository, PromoterRepository promoterRepository){
        this.labelRepository = labelRepository;
        this.memberRepository = memberRepository;
        this.genreService = genreService;
        this.locationRepository = locationRepository;
        this.promoterRepository = promoterRepository;
    }

    @PostMapping(value = "/api/label", consumes = "application/json")
    public ResponseEntity<?> postLabel(@RequestBody Label label){
        try {
            labelRepository.save(label);
            return new ResponseEntity<>(label, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/member", consumes = "application/json")
    public ResponseEntity<?> postMember(@RequestBody Member member){
        try {
            memberRepository.save(member);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/genre", consumes = "application/json")
    public ResponseEntity<?> postGenre(@RequestBody Genre genre){
        try {
            genreService.save(genre);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/location", consumes = "application/json")
    public ResponseEntity<?> postLocation(@RequestBody Location location){
        try {
            locationRepository.save(location);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/promoter", consumes = "application/json")
    public ResponseEntity<?> postPromoter(@RequestBody Promoter promoter){
        try {
            promoterRepository.save(promoter);
            return new ResponseEntity<>(promoter, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/recommend/{id}", produces = "application/json")
    public ResponseEntity<?> recommendBand (@PathVariable Long id){
        try {
            log.info(id.toString());
            return new ResponseEntity<>(genreService.recommendABand(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

