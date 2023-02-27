package com.guilhermeaureliano.rickandmortyapi.controller;

import com.guilhermeaureliano.rickandmortyapi.client.EpisodeClient;
import com.guilhermeaureliano.rickandmortyapi.response.EpisodeListResponse;
import com.guilhermeaureliano.rickandmortyapi.response.EpisodeResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class EpisodeController {

    private EpisodeClient episodeClient;

    @RequestMapping(value = "/episode/{id}", method = RequestMethod.GET)
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return this.episodeClient.getEpisodeById(id);
    }

    @RequestMapping(value = "/episode", method = RequestMethod.GET)
    public Flux<EpisodeListResponse> getAllEpisodes(@RequestParam(name = "page", defaultValue = "1") int page) {
        return this.episodeClient.getAllEpisodes(page);
    }

    @RequestMapping(value = "/episodes/{ids}", method = RequestMethod.GET)
    public Flux<EpisodeResponse> getMultipleEpisodes(@PathVariable String ids) {
        return this.episodeClient.getEpisodesById(ids);
    }
}
