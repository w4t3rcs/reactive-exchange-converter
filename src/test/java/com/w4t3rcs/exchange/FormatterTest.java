package com.w4t3rcs.exchange;

import com.w4t3rcs.exchange.dto.conversion.ConversionRequest;
import com.w4t3rcs.exchange.dto.provider.ExchangeProvider;
import com.w4t3rcs.exchange.service.formatter.EndpointFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FormatterTest {
    private final EndpointFormatter<ExchangeProvider, String, ConversionRequest> formatter;

    @Autowired
    public FormatterTest(EndpointFormatter<ExchangeProvider, String, ConversionRequest> formatter) {
        this.formatter = formatter;
    }

    @Test
    void format() {
        ConversionRequest request = new ConversionRequest("EUR", "USD", 3.1f);
        Assertions.assertEquals("https://api.exchangerate-api.com/v4/latest/EUR",
                formatter.format(ExchangeProvider.PROVIDER_1, request));
        Assertions.assertEquals("http://api.exchangeratesapi.io/v1/latest?access_key=9abbf9404bc492d41dc78c2abdb410d3",
                formatter.format(ExchangeProvider.PROVIDER_2, request));
    }
}
