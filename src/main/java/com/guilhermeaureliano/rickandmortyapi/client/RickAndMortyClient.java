package com.guilhermeaureliano.rickandmortyapi.client;

import com.guilhermeaureliano.rickandmortyapi.response.CharacterListResponse;
import com.guilhermeaureliano.rickandmortyapi.response.CharacterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {

    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> getCharacterById(String id) {
        log.info("Buscando o personagem com o ID [{}]", id);

        return webClient
                .get()
                .uri("/character/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique o ID informado!")))
                .bodyToMono(CharacterResponse.class);
    }

    public Flux<CharacterListResponse> getAllCharacters(int page) {
        log.info("Buscando todos os personagens");

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/character")
                        .queryParam("page", page)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Erro ao buscar personagens!")))
                .bodyToFlux(CharacterListResponse.class);
    }
}
