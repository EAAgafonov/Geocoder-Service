package com.geocoderproxyservice.geo_main;

import com.geocoderproxyservice.data.GeocoderData;
import com.geocoderproxyservice.repository.GeocoderDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
@Slf4j
public class GeoCoderFinal implements GeoCoder  {
    private final StringBuilder query = new StringBuilder();
    private final GeocoderDataRepository geocoderRepository;

    private final String apiKey = "871f819e-b3ff-4f50-80c2-8052601f93d5";
    private final String initialUrl = "https://geocode-maps.yandex.ru/1.x/?format=json&lang=ru_RU&apikey=" + apiKey;

    //retrieves data fromJSONArray
    public void formRequestFromJsonDataArray(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.getJSONObject(i).has("latitude")) {
                String latitude = jsonArray.getJSONObject(i).get("latitude").toString();
                String longitude = jsonArray.getJSONObject(i).get("longitude").toString();
                formRequestFromStringCoordinates(latitude, longitude);
            }
            else if (jsonArray.getJSONObject(i).has("city")) {
                String city = jsonArray.getJSONObject(i).get("city").toString();
                String street = jsonArray.getJSONObject(i).get("street").toString();
                String house = jsonArray.getJSONObject(i).get("house").toString();
                formRequestFromStringAddress(city, street, house);
            }
        }
    }

    public void formRequestFromStringAddress(String city, String street, String house) {
        query.setLength(0);
        String cityFormatted = addressFormatSplit(city);
        String streetFormatted = addressFormatSplit(street);
        String houseFormatted = addressFormatSplit(house);
        query.append(initialUrl)
                .append("&geocode=")
                .append(cityFormatted).append(",+")
                .append(streetFormatted).append(",+")
                .append(houseFormatted);
        sendRequest(query.toString(), "addresses");
    }

    public void formRequestFromStringCoordinates(String latitude, String longitude) {
        query.setLength(0);
        query.append(initialUrl).append("&sco=latlong").append("&geocode=").append(latitude).append(",").append(longitude);
        sendRequest(query.toString(), "coordinates");
    }

    //sends and receives a request
    private void sendRequest(String query, String type) {
        log.info("Sending a request to the server for the data");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(query)).build();

        CompletableFuture<String> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(result -> {
                if (result.statusCode() > 299)
                    log.error("Something went wrong. ServerCode is - " + result.statusCode());
                return result;
            })
            .thenApply(HttpResponse::body);

        if (type.equals("coordinates")) {
            completableFuture.thenApply(this::getAddress).join();
        }
        else if (type.equals("addresses")){
            completableFuture.thenApply(this::getCoordinates).join();
        }
    }

    //
    private String addressFormatSplit(Object str) {
        return String.join("+", str.toString()
                .trim().split("[^A-Za-zА-Яа-я0-9]+"));
    }

    //retrieves needed data and saves to db
    private String getAddress(String responseBody) {
        log.info("Retrieving address from the json response");
        JSONObject jsonObject = new JSONObject(responseBody);

        String[] mass = jsonObject.getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONObject("metaDataProperty").getJSONObject("GeocoderResponseMetaData").getJSONObject("Point").get("pos").toString().split(" ");
        double latitude = Double.parseDouble(mass[1]);
        double longitude = Double.parseDouble(mass[0]);
        GeocoderData data = new GeocoderData(
                null,
                latitude,
                longitude,
                jsonObject.getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONArray("featureMember").getJSONObject(0).getJSONObject("GeoObject").getJSONObject("metaDataProperty").getJSONObject("GeocoderMetaData").getJSONObject("Address").get("formatted").toString()
        );
        geocoderRepository.save(data);
        return "";
    }

    //retrieves needed data and saves to db
    private String getCoordinates(String responseBody) {
        log.info("Retrieving coordinates from the json response");
        JSONObject jsonObject = new JSONObject(responseBody);
        String[] mass = jsonObject.getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONArray("featureMember").getJSONObject(0).getJSONObject("GeoObject").getJSONObject("Point").get("pos").toString().split(" ");
        double latitude = Double.parseDouble(mass[1]);
        double longitude = Double.parseDouble(mass[0]);
        GeocoderData data = new GeocoderData(
                null,
                latitude,
                longitude,
                jsonObject.getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONObject("metaDataProperty").getJSONObject("GeocoderResponseMetaData").get("request").toString()
        );
        geocoderRepository.save(data);
        return "";
    }




}
