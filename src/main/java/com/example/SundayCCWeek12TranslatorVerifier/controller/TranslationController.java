package com.example.SundayCCWeek12TranslatorVerifier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TranslationController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
