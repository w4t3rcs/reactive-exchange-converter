package com.w4t3rcs.exchange.service.parser;

import com.w4t3rcs.exchange.dto.conversion.ConversionRequest;
import com.w4t3rcs.exchange.dto.conversion.ConversionResponse;
import com.w4t3rcs.exchange.dto.provider.ExchangeProvider;
import com.w4t3rcs.exchange.dto.provider.ProviderResponse;
import com.w4t3rcs.exchange.service.formatter.EndpointFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProviderConverter implements Converter<ConversionRequest, Mono<ConversionResponse>> {
    private final WebClient webClient;
    private final EndpointFormatter<ExchangeProvider, String, ConversionRequest> formatter;

    @Override
    public Mono<ConversionResponse> convert(ConversionRequest request, ExchangeProvider provider) {
        return webClient.get()
                .uri(formatter.format(provider, request))
                .retrieve()
                .bodyToMono(ProviderResponse.class)
                .map(info -> new ConversionResponse(
                        request.getFrom(),
                        request.getTo(),
                        request.getAmount(),
                        (float) Math.round(info.getRates().get(request.getTo()) * request.getAmount() * 100)/100
                ))
                .cache()
                .log();
    }
}
