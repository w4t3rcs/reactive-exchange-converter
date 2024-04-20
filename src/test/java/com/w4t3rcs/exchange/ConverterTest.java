package com.w4t3rcs.exchange;

import com.w4t3rcs.exchange.dto.conversion.ConversionRequest;
import com.w4t3rcs.exchange.dto.conversion.ConversionResponse;
import com.w4t3rcs.exchange.service.converter.Converter;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConverterTest {
    private final Converter<ConversionRequest, Mono<ConversionResponse>> converter;

    @Autowired
    public ConverterTest(Converter<ConversionRequest, Mono<ConversionResponse>> converter) {
        this.converter = converter;
    }

    @RepeatedTest(5)
    void convert() {
        ConversionRequest request = new ConversionRequest("USD", "EUR", 4f);
        Mono<Float> responseMono = converter.convert(request)
                .map(ConversionResponse::getConverted);
        StepVerifier.create(responseMono)
                .expectNextMatches(conversionResponse -> conversionResponse == 3.75f || conversionResponse == 4.0f)
                .verifyComplete();
    }
}
