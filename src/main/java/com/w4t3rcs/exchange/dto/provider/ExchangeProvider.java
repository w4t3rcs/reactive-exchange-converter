package com.w4t3rcs.exchange.dto.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExchangeProvider {
    PROVIDER_1("https://api.exchangerate-api.com/v4/latest/%s"),
    PROVIDER_2("https://api.exchangeratesapi.io/v1/latest?access_key=9abbf9404bc492d41dc78c2abdb410d3&base=%s");

    private final String uri;
}
