package com.engsoft.marmita.service.integration;

import com.engsoft.marmita.model.integration.GeocodeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleGeocodingService {

    private final WebClient webClient;

    @Value("${google.api.key}")
    private String apiKey; // Valor definido no application.properties

    public GoogleGeocodingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://maps.googleapis.com/maps/api/geocode").build();
    }

    public String getLocation(String address) {
        String url = UriComponentsBuilder.fromPath("/json")
            .queryParam("address", address)
            .queryParam("key", apiKey)
            .toUriString();

        return this.webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    public GeocodeResponse.Location getCoordinates(String address) {
        String url = UriComponentsBuilder.fromPath("/json")
                .queryParam("address", address)
                .queryParam("key", apiKey)
                .toUriString();

        GeocodeResponse response = this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(GeocodeResponse.class)
                .block();

        if (response != null && !response.getResults().isEmpty()) {
            return response.getResults().get(0).getGeometry().getLocation();
        } else {
            throw new RuntimeException("Endereço não encontrado ou erro na API.");
        }
    }
}