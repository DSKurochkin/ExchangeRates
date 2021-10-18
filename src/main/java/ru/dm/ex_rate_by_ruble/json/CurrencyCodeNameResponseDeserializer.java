package ru.dm.ex_rate_by_ruble.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.dm.ex_rate_by_ruble.model.CurrencyCodeNameResponse;

import java.io.IOException;
import java.util.LinkedHashMap;

public class CurrencyCodeNameResponseDeserializer extends JsonDeserializer<CurrencyCodeNameResponse> {

    @Override
    public CurrencyCodeNameResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TypeReference<LinkedHashMap<String, String>> typeRef
                = new TypeReference<LinkedHashMap<String, String>>() {
        };
        LinkedHashMap<String, String> CurrencyCodeNameMap = p.getCodec().readValue(p, typeRef);
        CurrencyCodeNameResponse response = new CurrencyCodeNameResponse();
        response.setMap(CurrencyCodeNameMap);
        return response;
    }
}
