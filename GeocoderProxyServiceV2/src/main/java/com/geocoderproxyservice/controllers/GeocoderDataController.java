package com.geocoderproxyservice.controllers;

import com.geocoderproxyservice.data.Response;
import com.geocoderproxyservice.service.GeocoderDataService;
import com.geocoderproxyservice.service.TracesDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDate.now;
import static java.util.Map.of;

@RestController
@RequestMapping(path = "/data")
@RequiredArgsConstructor
public class GeocoderDataController {
    private final GeocoderDataService geocoderDataService;
    private final TracesDataService tracesDataService;

    @GetMapping()
    public ResponseEntity<Response> getData() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("dataDBs", geocoderDataService.getData()))
                        .traceData(of("traces", tracesDataService.getTraces()))
                        .build()
        );
    }

    @PostMapping("/dt/final")
    public ResponseEntity<Response> postDataFinal(@RequestBody Object data) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("dataDBs", geocoderDataService.postDataFinal(data)))
                        .traceData(of("traces", tracesDataService.getTraces()))
                        .build()
        );
    }

    @GetMapping("/dt/c/{val1}+{val2}")
    public ResponseEntity<Response> sendCoordinates(@PathVariable("val1") String val1, @PathVariable("val2") String val2) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("dataDBs", geocoderDataService.sendCoordinates(val1, val2)))
                        .traceData(of("traces", tracesDataService.getTraces()))
                        .build()
        );
    }

    @GetMapping("/dt/a/{val1}+{val2}+{val3}")
    public ResponseEntity<Response> sendAddress(@PathVariable("val1") String val1, @PathVariable("val2") String val2, @PathVariable("val3") String val3) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("dataDBs", geocoderDataService.sendAddress(val1, val2, val3)))
                        .traceData(of("traces", tracesDataService.getTraces()))
                        .build()
        );
    }



}
