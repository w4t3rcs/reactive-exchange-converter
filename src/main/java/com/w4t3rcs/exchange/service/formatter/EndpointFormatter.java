package com.w4t3rcs.exchange.service.formatter;

public interface EndpointFormatter<K, V, R> {
    V format(K k, R r);
}
