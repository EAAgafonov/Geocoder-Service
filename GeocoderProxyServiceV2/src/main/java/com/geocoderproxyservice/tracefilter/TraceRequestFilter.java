package com.geocoderproxyservice.tracefilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.trace.http.HttpExchangeTracer;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class TraceRequestFilter extends HttpTraceFilter {

    public TraceRequestFilter(HttpTraceRepository repository, HttpExchangeTracer tracer) { super(repository, tracer); }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        return !(request.getServletPath().contains("data/dt"));
    }


}