package com.bairei.ormapp.controllers.api;

import com.bairei.ormapp.models.*;
import com.bairei.ormapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final LabelRepository labelRepository;
    private final MemberRepository memberRepository;
    private final GenreRepository genreRepository;
    private final LocationRepository locationRepository;
    private final PromoterRepository promoterRepository;

    @Autowired
    public ApiController(LabelRepository labelRepository, MemberRepository memberRepository, GenreRepository genreRepository,
                         LocationRepository locationRepository, VenueRepository venueRepository, PromoterRepository promoterRepository){
        this.labelRepository = labelRepository;
        this.memberRepository = memberRepository;
        this.genreRepository = genreRepository;
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
            genreRepository.save(genre);
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
}

