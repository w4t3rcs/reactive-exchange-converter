package com.w4t3rcs.exchange.service.converter;

public interface Converter<K, V> {
    V convert(K k);
}
