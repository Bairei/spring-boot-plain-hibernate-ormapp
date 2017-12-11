package com.bairei.ormapp.controllers.api;

import com.bairei.ormapp.models.*;
import com.bairei.ormapp.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class ApiController {

    private final LabelService labelService;
    private final MemberService memberService;
    private final GenreService genreService;
    private final LocationService locationService;
    private final PromoterService promoterService;

    @Autowired
    public ApiController(LabelService labelService, MemberService memberService, GenreService genreService,
                         LocationService locationService, PromoterService promoterService){
        this.labelService = labelService;
        this.memberService = memberService;
        this.genreService = genreService;
        this.locationService = locationService;
        this.promoterService = promoterService;
    }

    @PostMapping(value = "/api/label", consumes = "application/json")
    public ResponseEntity<?> postLabel(@RequestBody @Valid Label label, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            labelService.save(label);
            return new ResponseEntity<>(label, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/member", consumes = "application/json")
    public ResponseEntity<?> postMember(@RequestBody @Valid Member member, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            memberService.save(member);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/genre", consumes = "application/json")
    public ResponseEntity<?> postGenre(@RequestBody @Valid Genre genre, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            genreService.save(genre);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/location", consumes = "application/json")
    public ResponseEntity<?> postLocation(@RequestBody @Valid Location location, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            locationService.save(location);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/promoter", consumes = "application/json")
    public ResponseEntity<?> postPromoter(@RequestBody @Valid Promoter promoter, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            promoterService.save(promoter);
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