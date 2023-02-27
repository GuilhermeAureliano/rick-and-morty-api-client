package com.guilhermeaureliano.rickandmortyapi.controller;

import com.guilhermeaureliano.rickandmortyapi.client.RickAndMortyClient;
import com.guilhermeaureliano.rickandmortyapi.response.CharacterResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    private RickAndMortyClient rickAndMortyClient;

    @RequestMapping(value = "/character/{id}", method = RequestMethod.GET)
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return this.rickAndMortyClient.getCharacterById(id);
    }
}
