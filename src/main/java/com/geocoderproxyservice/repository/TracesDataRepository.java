package com.geocoderproxyservice.repository;

import com.geocoderproxyservice.data.TracesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TracesDataRepository
        extends JpaRepository<TracesData, Long> {
}
