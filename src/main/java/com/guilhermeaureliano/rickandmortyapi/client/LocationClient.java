package com.guilhermeaureliano.rickandmortyapi.client;

import com.guilhermeaureliano.rickandmortyapi.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LocationClient {
    private final WebClient webClient;

    public LocationClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<LocationResponse> getLocationById(String id) {
        log.info("Buscando o local com o ID [{}]", id);

        return webClient
                .get()
                .uri("/location/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique o ID informado!")))
                .bodyToMono(LocationResponse.class);
    }

}
