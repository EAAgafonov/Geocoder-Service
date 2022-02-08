package com.geocoderproxyservice.geo_main;

import org.json.JSONArray;

public interface GeoCoder {

    void formRequestFromJsonDataArray(JSONArray jsonArray);
    void formRequestFromStringAddress(String city, String street, String house);
    void formRequestFromStringCoordinates(String latitude, String longitude);
}
