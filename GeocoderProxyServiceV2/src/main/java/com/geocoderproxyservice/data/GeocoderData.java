package com.geocoderproxyservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "geocoder_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeocoderData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "user_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private double latitude;
    private double longitude;
    private String address;


}
