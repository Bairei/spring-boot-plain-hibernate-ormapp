package com.bairei.ormapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexRepository {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
