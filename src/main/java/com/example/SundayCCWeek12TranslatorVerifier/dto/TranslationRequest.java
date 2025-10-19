package com.example.SundayCCWeek12TranslatorVerifier.dto;

import java.util.List;

public class TranslationRequest {
    private String originalText;
    private List<UserTranslation> translations;

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public List<UserTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<UserTranslation> translations) {
        this.translations = translations;
    }
}



