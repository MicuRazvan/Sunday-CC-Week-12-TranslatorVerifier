package com.example.SundayCCWeek12TranslatorVerifier.dto;

public class TranslationResponse {
    private String lang;
    private boolean correct;
    private String correctedText;

    public TranslationResponse(String lang, boolean correct, String correctedText) {
        this.lang = lang;
        this.correct = correct;
        this.correctedText = correctedText;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getCorrectedText() {
        return correctedText;
    }

    public void setCorrectedText(String correctedText) {
        this.correctedText = correctedText;
    }
}
