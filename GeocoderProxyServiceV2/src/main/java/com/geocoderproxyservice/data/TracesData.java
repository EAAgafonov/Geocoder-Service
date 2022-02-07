package com.geocoderproxyservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "traces_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TracesData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name="product_generator", sequenceName = "product_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String timestamp;
    private String method;
    private String timeTaken;
    private int status;
    private String uri;
}
