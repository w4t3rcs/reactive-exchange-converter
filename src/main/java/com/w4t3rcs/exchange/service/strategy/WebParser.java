package com.w4t3rcs.exchange.service.strategy;

import com.w4t3rcs.exchange.dto.provider.ExchangeProvider;

public interface WebParser<R, T> {
    T respond(R r, ExchangeProvider provider);
}
