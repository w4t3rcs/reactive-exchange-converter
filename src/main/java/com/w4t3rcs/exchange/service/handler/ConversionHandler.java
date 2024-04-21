package com.w4t3rcs.exchange.service.handler;

public interface ConversionHandler<K, V> {
    V convert(K k);
}
