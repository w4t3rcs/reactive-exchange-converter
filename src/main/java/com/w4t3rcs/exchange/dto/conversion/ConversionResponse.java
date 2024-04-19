package com.w4t3rcs.exchange.dto.conversion;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ConversionResponse implements Serializable {
    private String from;
    private String to;
    private float amount;
    private float converted;
}
