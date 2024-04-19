package com.w4t3rcs.exchange.controller;

import com.w4t3rcs.exchange.dto.conversion.ConversionRequest;
import com.w4t3rcs.exchange.dto.conversion.ConversionResponse;
import com.w4t3rcs.exchange.exception.ProviderException;
import com.w4t3rcs.exchange.service.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/currency/convert")
@RequiredArgsConstructor
public class ConversionController {
    private final Converter<ConversionRequest, Mono<ConversionResponse>> service;

    @PostMapping
    public Mono<ResponseEntity<ConversionResponse>> convertCurrency(@RequestBody ConversionRequest request) {
        return service.convert(request)
                .map(ResponseEntity::ok)
                .doOnError(throwable -> {
                    throw new ProviderException(throwable.getMessage());
                });
    }
}
