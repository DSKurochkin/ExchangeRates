package ru.dm.ex_rate_by_ruble.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
public class ExchangeRateResponse {
    @Setter
    private String disclaimer;

    @Setter
    private String license;

    @Setter
    private long timestamp;

    @Setter
    private String base;

    @Setter
    @Getter
    @JsonDeserialize
    private Map<String, Double> rates;

}
