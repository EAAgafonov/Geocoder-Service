package com.geocoderproxyservice.service;

import com.geocoderproxyservice.data.GeocoderData;
import com.geocoderproxyservice.geo_main.GeoCoder;
import com.geocoderproxyservice.repository.GeocoderDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeocoderDataService {
    private final GeocoderDataRepository geocoderRepository;
    private final GeoCoder geoFinal;

    public Collection<GeocoderData> getData() {
        log.info("Fetching all the data on startup/reload");
        return geocoderRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Collection<GeocoderData> postDataFinal(Object data) {
        log.info("Getting data from front and sending a request");
        String dt = data.toString().replaceAll("=", ":");
        geoFinal.formRequestFromJsonDataArray(new JSONArray(dt));
        return geocoderRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Collection<GeocoderData> sendCoordinates(String val1, String val2) {
        log.info("Forming and sending a request with coordinates to get the addresses");
        geoFinal.formRequestFromStringCoordinates(val1, val2);
        return geocoderRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Collection<GeocoderData> sendAddress(String val1, String val2, String val3) {
        log.info("Forming and sending a request with addresses to get coordinates");
        geoFinal.formRequestFromStringAddress(val1, val2, val3);
        return geocoderRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }



}
