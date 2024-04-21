package com.w4t3rcs.exchange.service.parser;

import com.w4t3rcs.exchange.dto.provider.ExchangeProvider;

public interface Converter<R, T> {
    T respond(R r, ExchangeProvider provider);
}
