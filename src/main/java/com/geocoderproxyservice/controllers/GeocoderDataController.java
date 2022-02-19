package com.geocoderproxyservice.controllers;

import com.geocoderproxyservice.data.GeocoderData;
import com.geocoderproxyservice.service.GeocoderDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/data")
@RequiredArgsConstructor
@Tag(name = "Geocoder Controller")
public class GeocoderDataController {
    private final GeocoderDataService geocoderDataService;
//    private final TracesDataService tracesDataService;

    @Operation(summary = "Get data from Db")
    @GetMapping("/get_all")
    public Collection<GeocoderData> getDataFromDb() {
            return geocoderDataService.getDataFromDb();
    }

    @Operation(summary = "Get coordinates from address")
    @GetMapping("/get_coordinates")
    public ResponseEntity<Object> getCoordinatesFromAddress(
            @RequestParam(name = "city", defaultValue = "Москва") String city,
            @RequestParam(name = "street", defaultValue = "Моховая") String street,
            @RequestParam(name = "house", defaultValue = "10") String house
    ) {
        return geocoderDataService.getCoordinatesFromAddress(city, street, house);
    }

    @Operation(summary = "Get address from coordinates")
    @GetMapping("/get_address")
    public ResponseEntity<Object> getAddressFromCoordinates(
            @RequestParam(name = "latitude", defaultValue = "55.12369") String latitude,
            @RequestParam(name = "longitude", defaultValue = "37.12369") String longitude
    ) {
        return geocoderDataService.getAddressFromCoordinates(latitude, longitude);
    }



}
