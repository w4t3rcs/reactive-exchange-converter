package com.w4t3rcs.exchange.service.converter;

import com.w4t3rcs.exchange.dto.conversion.ConversionRequest;
import com.w4t3rcs.exchange.dto.conversion.ConversionResponse;
import com.w4t3rcs.exchange.dto.provider.ExchangeProvider;
import com.w4t3rcs.exchange.dto.random.RandomizedPair;
import com.w4t3rcs.exchange.service.parser.WebParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ConverterService implements Converter<ConversionRequest, Mono<ConversionResponse>> {
    private final WebParser<ConversionRequest, Mono<ConversionResponse>> webParser;

    @Autowired
    public ConverterService(WebParser<ConversionRequest, Mono<ConversionResponse>> webParser) {
        this.webParser = webParser;
    }

    @Override
    @Cacheable(value = "ConverterService::convert", key = "#request.from" + "." + "#request.to")
    public Mono<ConversionResponse> convert(ConversionRequest request) {
        RandomizedPair<ExchangeProvider> pathPair = RandomizedPair.of(
                ExchangeProvider.PROVIDER_1, ExchangeProvider.PROVIDER_2
        );

        if (pathPair.getFirst() == ExchangeProvider.PROVIDER_1) {
            return convert(request, ExchangeProvider.PROVIDER_1, ExchangeProvider.PROVIDER_2);
        } else {
            return convert(request, ExchangeProvider.PROVIDER_2, ExchangeProvider.PROVIDER_1);
        }
    }

    private Mono<ConversionResponse> convert(ConversionRequest request, ExchangeProvider defaultProvider, ExchangeProvider alternate) {
        return webParser.respond(request, defaultProvider)
                .onErrorComplete()
                .switchIfEmpty(webParser.respond(request, alternate));
    }
}