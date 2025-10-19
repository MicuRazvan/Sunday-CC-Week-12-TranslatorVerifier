package com.example.SundayCCWeek12TranslatorVerifier.service;

import com.example.SundayCCWeek12TranslatorVerifier.dto.DeepLLanguage;
import com.example.SundayCCWeek12TranslatorVerifier.dto.DeepLResponse;
import com.example.SundayCCWeek12TranslatorVerifier.dto.TranslationRequest;
import com.example.SundayCCWeek12TranslatorVerifier.dto.TranslationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslationService {

    private final WebClient webClient;

    @Value("${DEEPL_API_KEY}")
    private String deeplApiKey;

    public TranslationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api-free.deepl.com").build();
    }

    public List<TranslationResponse> verifyTranslations(TranslationRequest request) {
        return request.getTranslations().stream()
                .map(userTranslation -> {
                    String deeplTranslation = translateWithDeepL(request.getOriginalText(), userTranslation.getLang()).block();
                    boolean isCorrect = deeplTranslation != null && deeplTranslation.equalsIgnoreCase(userTranslation.getText().trim());
                    return new TranslationResponse(userTranslation.getLang(), isCorrect, isCorrect ? "" : deeplTranslation);
                })
                .collect(Collectors.toList());
    }

    private Mono<String> translateWithDeepL(String text, String targetLang) {
        // the body for the DeepL API call
        String requestBody = String.format("{\"text\":[\"%s\"],\"target_lang\":\"%s\"}", text, targetLang);

        return this.webClient.post()
                .uri("/v2/translate")
                .header("Authorization", "DeepL-Auth-Key " + deeplApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(DeepLResponse.class)
                .map(response -> response.getTranslations().get(0).getText());
    }

    public Mono<List<DeepLLanguage>> getSupportedLanguages() {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/languages")
                        .queryParam("type", "target")
                        .build())
                .header("Authorization", "DeepL-Auth-Key " + deeplApiKey)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<DeepLLanguage>>() {});
    }
}
