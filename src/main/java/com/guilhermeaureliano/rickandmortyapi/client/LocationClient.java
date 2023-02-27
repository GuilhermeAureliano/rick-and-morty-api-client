package com.guilhermeaureliano.rickandmortyapi.client;

import com.guilhermeaureliano.rickandmortyapi.response.LocationListResponse;
import com.guilhermeaureliano.rickandmortyapi.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
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

    public Flux<LocationListResponse> getAllLocation(int page) {
        log.info("Buscando todos os locais da página [{}]", page);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/location")
                        .queryParam("page", page)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Erro ao buscar locais!")))
                .bodyToFlux(LocationListResponse.class);
    }

    public Flux<LocationResponse> getLocationsById(String ids) {
        log.info("Buscando os locais com os IDs [{}]", ids);

        return webClient
                .get()
                .uri("/location/" + ids)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique o ID informado!")))
                .bodyToFlux(LocationResponse.class);
    }

}
