package com.w4t3rcs.exchange;

import com.w4t3rcs.exchange.dto.conversion.ConversionRequest;
import com.w4t3rcs.exchange.dto.conversion.ConversionResponse;
import com.w4t3rcs.exchange.dto.provider.ExchangeProvider;
import com.w4t3rcs.exchange.service.parser.Converter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConverterTest {
    private final Converter<ConversionRequest, Mono<ConversionResponse>> parser;

    @Autowired
    public ConverterTest(Converter<ConversionRequest, Mono<ConversionResponse>> parser) {
        this.parser = parser;
    }

    @Test
    void parse() {
        ConversionRequest request = new ConversionRequest("USD", "EUR", 4f);
        Mono<ConversionResponse> responseMono = parser.respond(request, ExchangeProvider.PROVIDER_1);
        StepVerifier.create(responseMono)
                .expectNext(new ConversionResponse("USD", "EUR", 4f, 3.75f))
                .verifyComplete();
    }
}
