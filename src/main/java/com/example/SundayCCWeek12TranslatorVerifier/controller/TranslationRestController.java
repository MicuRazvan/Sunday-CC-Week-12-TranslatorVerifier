package com.example.SundayCCWeek12TranslatorVerifier.controller;

import com.example.SundayCCWeek12TranslatorVerifier.dto.DeepLLanguage;
import com.example.SundayCCWeek12TranslatorVerifier.dto.TranslationRequest;
import com.example.SundayCCWeek12TranslatorVerifier.dto.TranslationResponse;
import com.example.SundayCCWeek12TranslatorVerifier.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class TranslationRestController {

    private final TranslationService translationService;

    @Autowired
    public TranslationRestController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("/verify")
    public List<TranslationResponse> testTranslations(@RequestBody TranslationRequest request) {
        return translationService.verifyTranslations(request);
    }

    @GetMapping("/api/languages")
    public Mono<List<DeepLLanguage>> getLanguages() {
        return translationService.getSupportedLanguages();
    }
}
