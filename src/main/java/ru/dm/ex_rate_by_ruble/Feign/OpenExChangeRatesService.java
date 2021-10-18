package ru.dm.ex_rate_by_ruble.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dm.ex_rate_by_ruble.model.CurrencyCodeNameResponse;
import ru.dm.ex_rate_by_ruble.model.ExchangeRateResponse;

@FeignClient(value = "Openexchangerates", url = "${url.openexchangerates.api}")
public interface OpenExChangeRatesService {
    String apiId = "e90abb7c3554474e8276f2d012855faa";

    @GetMapping("/latest.json?app_id=" + "${key.openexchangerates.api}")
    ExchangeRateResponse getToday();

    @GetMapping("/historical/{date}?app_id=" + "${key.openexchangerates.api}")
    ExchangeRateResponse getYesterday(@PathVariable String date);

    @GetMapping("/currencies.json?app_id=" + "${key.openexchangerates.api}")
    CurrencyCodeNameResponse getCurrencySymbols();

}
