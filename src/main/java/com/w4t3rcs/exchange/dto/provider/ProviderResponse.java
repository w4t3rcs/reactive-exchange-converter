package com.w4t3rcs.exchange.dto.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderResponse {
    private String base;
    private Map<String, Float> rates;
}
