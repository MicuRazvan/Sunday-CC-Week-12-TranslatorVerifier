package com.example.SundayCCWeek12TranslatorVerifier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeepLTranslation {
    @JsonProperty("detected_source_language")
    private String detectedSourceLanguage;
    private String text;

    public String getDetectedSourceLanguage() {
        return detectedSourceLanguage;
    }

    public void setDetectedSourceLanguage(String detectedSourceLanguage) {
        this.detectedSourceLanguage = detectedSourceLanguage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
