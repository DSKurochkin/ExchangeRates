package ru.dm.ex_rate_by_ruble.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dm.ex_rate_by_ruble.json.CurrencyCodeNameResponseDeserializer;

import java.util.LinkedHashMap;

@NoArgsConstructor
@JsonDeserialize(using = CurrencyCodeNameResponseDeserializer.class)
public class CurrencyCodeNameResponse {
    @Getter
    @Setter
    LinkedHashMap<String, String> map;
}
