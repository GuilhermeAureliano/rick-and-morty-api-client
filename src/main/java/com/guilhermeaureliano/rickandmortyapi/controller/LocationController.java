package com.guilhermeaureliano.rickandmortyapi.controller;

import com.guilhermeaureliano.rickandmortyapi.client.LocationClient;
import com.guilhermeaureliano.rickandmortyapi.response.LocationListResponse;
import com.guilhermeaureliano.rickandmortyapi.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class LocationController {

    private LocationClient locationClient;

    @RequestMapping(value = "location/{id}", method = RequestMethod.GET)
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return this.locationClient.getLocationById(id);
    }

    @RequestMapping(value = "location", method = RequestMethod.GET)
    public Flux<LocationListResponse> getAllLocation(@RequestParam(name = "page", defaultValue = "1") int page) {
        return this.locationClient.getAllLocation(page);
    }
}
