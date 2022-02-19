package com.geocoderproxyservice.service;

import com.geocoderproxyservice.data.GeocoderData;
import com.geocoderproxyservice.feign.FeignGeocoderClient;
import com.geocoderproxyservice.repository.GeocoderDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeocoderDataService {
    private final FeignGeocoderClient feignGeocoderClient;
    private final GeocoderDataRepository repository;

    @Value("${ya.api.key}")
    String apikey;
    String responseFormat = "json";
    String resultsNum = "1";
    String sco = "latlong";

    public Collection<GeocoderData> getDataFromDb() {
        log.info("GET data from db");
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public ResponseEntity<Object> getCoordinatesFromAddress(String city, String street, String house) {
        log.info("Coordinates from address");
        ResponseEntity<Object> responseEntity = feignGeocoderClient.getCoordinatesFromAddress(apikey, city + "," + street + "," + house, responseFormat, resultsNum);
        addToDB(responseEntity, "address");
        return responseEntity;
    }

    public ResponseEntity<Object> getAddressFromCoordinates(String latitude, String longitude) {
        log.info("Address from coordinates");
        ResponseEntity<Object> responseEntity = feignGeocoderClient.getAddressFromCoordinates(apikey, latitude + "," + longitude, responseFormat, resultsNum, sco);
        addToDB(responseEntity, "coordinates");
        return responseEntity;
    }




    private void addToDB(ResponseEntity<Object> response, String type) {
        log.info("Adding data to db");
        JSONObject jsonObject = new JSONObject(response);
        String request = jsonObject.getJSONObject("body").getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONObject("metaDataProperty").getJSONObject("GeocoderResponseMetaData").get("request").toString();
        GeocoderData data = new GeocoderData();

        if (type.equals("address")) {
            data.setAddress(request);
            String[] mass = jsonObject.getJSONObject("body").getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONArray("featureMember").getJSONObject(0).getJSONObject("GeoObject").getJSONObject("Point").get("pos").toString().split(" ");
            data.setLatitude(Double.parseDouble(mass[1]));
            data.setLongitude(Double.parseDouble(mass[0]));
            repository.save(data);
        }
        else if (type.equals("coordinates")) {
            data.setAddress(jsonObject.getJSONObject("body").getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONArray("featureMember").getJSONObject(0).getJSONObject("GeoObject").getJSONObject("metaDataProperty").getJSONObject("GeocoderMetaData").getJSONObject("Address").get("formatted").toString());
            String[] mass = request.split(",");
            data.setLatitude(Double.parseDouble(mass[0]));
            data.setLongitude(Double.parseDouble(mass[1]));
            repository.save(data);
        }
    }




}
