package com.geocoderproxyservice.repository;

import com.geocoderproxyservice.data.TracesData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class CustomTraceRepository implements HttpTraceRepository {
    List<HttpTrace> traces = new ArrayList<>();
    private final TracesDataRepository tracesDataRepository;

    @Override
    public List<HttpTrace> findAll() {
        return traces;
    }

    @Override
    public void add(HttpTrace trace) {
        if ("GET".equals(trace.getRequest().getMethod()) || "POST".equals(trace.getRequest().getMethod()) || "OPTIONS".equals(trace.getRequest().getMethod())) {
            TracesData data = new TracesData(
                    null,
                    Timestamp.from(trace.getTimestamp()).toLocalDateTime().toString().replaceAll("T", " ").split("\\.")[0],
                    trace.getRequest().getMethod(),
                    trace.getTimeTaken().toString() + "ms",
                    trace.getResponse().getStatus(),
                    trace.getRequest().getUri().getPath()
            );
            tracesDataRepository.save(data);
            traces.add(trace);
        }
    }
}
