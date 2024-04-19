package com.w4t3rcs.exchange.service.formatter;

import com.w4t3rcs.exchange.dto.conversion.ConversionRequest;
import com.w4t3rcs.exchange.dto.provider.ExchangeProvider;
import org.springframework.stereotype.Service;

@Service
public class ProviderEndpointFormatter implements EndpointFormatter<ExchangeProvider, String, ConversionRequest> {
    @Override
    public String format(ExchangeProvider provider, ConversionRequest request) {
        return provider.getUri()
                .formatted(request.getFrom());
    }
}
