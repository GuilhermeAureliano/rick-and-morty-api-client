package com.guilhermeaureliano.rickandmortyapi.controller;

import com.guilhermeaureliano.rickandmortyapi.client.CharacterClient;
import com.guilhermeaureliano.rickandmortyapi.response.CharacterListResponse;
import com.guilhermeaureliano.rickandmortyapi.response.CharacterResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class CharacterController {

    private CharacterClient rickAndMortyClient;

    @RequestMapping(value = "/character/{id}", method = RequestMethod.GET)
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return this.rickAndMortyClient.getCharacterById(id);
    }

    @RequestMapping(value = "/character", method = RequestMethod.GET)
    public Flux<CharacterListResponse> getAllCharacters(@RequestParam(name = "page", defaultValue = "1") int page) {
        return this.rickAndMortyClient.getAllCharacters(page);
    }

    @RequestMapping(value = "/characters/{ids}", method = RequestMethod.GET)
    public Flux<CharacterResponse> getMultipleCharacters(@PathVariable String ids) {
        return this.rickAndMortyClient.getCharactersByIds(ids);
    }

}
