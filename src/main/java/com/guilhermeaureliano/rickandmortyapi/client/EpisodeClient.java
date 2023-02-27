package com.guilhermeaureliano.rickandmortyapi.client;

import com.guilhermeaureliano.rickandmortyapi.response.EpisodeListResponse;
import com.guilhermeaureliano.rickandmortyapi.response.EpisodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EpisodeClient {

    private final WebClient webClient;

    public EpisodeClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<EpisodeResponse> getEpisodeById(String id) {
        log.info("Buscando o episódio com o ID [{}]", id);

        return webClient
                .get()
                .uri("/episode/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique o ID informado!")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<EpisodeListResponse> getAllEpisodes(int page) {
        log.info("Buscando todos os episódios da página [{}]", page);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/episode")
                        .queryParam("page", page)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Erro ao buscar episódios!")))
                .bodyToFlux(EpisodeListResponse.class);
    }
}
