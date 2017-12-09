package com.bairei.ormapp.controllers.api;

import com.bairei.ormapp.models.Genre;
import com.bairei.ormapp.models.Label;
import com.bairei.ormapp.models.Member;
import com.bairei.ormapp.repositories.GenreRepository;
import com.bairei.ormapp.repositories.LabelRepository;
import com.bairei.ormapp.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final LabelRepository labelRepository;
    private final MemberRepository memberRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public ApiController(LabelRepository labelRepository, MemberRepository memberRepository, GenreRepository genreRepository){
        this.labelRepository = labelRepository;
        this.memberRepository = memberRepository;
        this.genreRepository = genreRepository;
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
}

