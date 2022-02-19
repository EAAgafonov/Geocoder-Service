package com.geocoderproxyservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ya", url = "${ya.url}")
public interface FeignGeocoderClient {


    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getCoordinatesFromAddress(
            @RequestParam("apikey") String apikey,
            @RequestParam("geocode") String geocode,
            @RequestParam("format") String format,
            @RequestParam("results") String results
    );

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getAddressFromCoordinates(
            @RequestParam("apikey") String apikey,
            @RequestParam("geocode") String geocode,
            @RequestParam("format") String format,
            @RequestParam("results") String results,
            @RequestParam("sco") String sco
    );
}
