package com.geocoderproxyservice;

import com.geocoderproxyservice.data.GeocoderData;
import com.geocoderproxyservice.data.TracesData;
import com.geocoderproxyservice.repository.GeocoderDataRepository;
import com.geocoderproxyservice.repository.TracesDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class GeocoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeocoderApplication.class, args);
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
                    "100ms",
                    200,
                    "/data/test/"
            );
            T_repository.save(test2);

        };
    }

}
