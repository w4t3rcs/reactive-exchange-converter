package com.w4t3rcs.exchange.dto.random;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomizedPair<T> {
    private T first;
    private T second;

    public static <T> RandomizedPair<T> of(T v1, T v2) {
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomNumber == 0) {
            return new RandomizedPair<>(v1, v2);
        } else {
            return new RandomizedPair<>(v2, v1);
        }
    }
}
