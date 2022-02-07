package com.geocoderproxyservice;

import com.geocoderproxyservice.data.GeocoderData;
import com.geocoderproxyservice.data.TracesData;
import com.geocoderproxyservice.repository.GeocoderDataRepository;
import com.geocoderproxyservice.repository.TracesDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@Configuration
@EnableSpringDataWebSupport
public class GeocoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeocoderApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

    @Bean
    CommandLineRunner commandLineRunner(GeocoderDataRepository G_repository, TracesDataRepository T_repository) {
        return args -> {
            GeocoderData test1 = new GeocoderData(
                    null,
                    48.851008,
                    2.398901,
                    " Paris, Bd de Charonne, 40"
            );
            G_repository.save(test1);

            TracesData test2 = new TracesData(
                    null,
                    "2022-02-03 20:18:06",
                    "POST",
                    "120ms",
                    200,
                    "/data/dt/"
            );
            T_repository.save(test2);

        };
    }

}
