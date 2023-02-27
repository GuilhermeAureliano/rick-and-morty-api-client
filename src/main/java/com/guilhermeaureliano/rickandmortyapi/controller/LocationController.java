package com.guilhermeaureliano.rickandmortyapi.controller;

import com.guilhermeaureliano.rickandmortyapi.client.LocationClient;
import com.guilhermeaureliano.rickandmortyapi.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
}
