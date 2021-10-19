package ru.dm.ex_rate_by_ruble.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dm.ex_rate_by_ruble.model.CurrencyCodeNameResponse;
import ru.dm.ex_rate_by_ruble.model.ExchangeRateResponse;

@FeignClient(value = "Openexchangerates", url = "${url.openexchangerates.api}")
public interface OpenExChangeRatesService {

    @GetMapping("/latest.json?app_id=" + "${key.openexchangerates.api}")
    ExchangeRateResponse getToday();

    @GetMapping("/historical/{date}?app_id=" + "${key.openexchangerates.api}")
    ExchangeRateResponse getYesterday(@PathVariable String date);

    @GetMapping("/currencies.json?app_id=" + "${key.openexchangerates.api}")
    CurrencyCodeNameResponse getCurrencyCodes();

}
