package com.geocoderproxyservice.service;

import com.geocoderproxyservice.data.TracesData;
import com.geocoderproxyservice.repository.TracesDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class TracesDataService {
    private final TracesDataRepository repository;

    public Collection<TracesData> getTraces() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public TracesData saveTrace(TracesData data) {
        return repository.save(data);
    }


}

