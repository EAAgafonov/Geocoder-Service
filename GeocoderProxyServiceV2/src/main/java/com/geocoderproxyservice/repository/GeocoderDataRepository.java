package com.geocoderproxyservice.repository;

import com.geocoderproxyservice.data.GeocoderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeocoderDataRepository
        extends JpaRepository<GeocoderData, Long> {

}
