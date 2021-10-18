package ru.dm.ex_rate_by_ruble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.ex_rate_by_ruble.Feign.GiphyService;
import ru.dm.ex_rate_by_ruble.Feign.OpenExChangeRatesService;
import ru.dm.ex_rate_by_ruble.model.ExchangeRateResponse;
import ru.dm.ex_rate_by_ruble.model.Gif.SimpleGif;
import ru.dm.ex_rate_by_ruble.util.ExchangeRateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class Controller {
    @Value("${code.currency.app}")
    private String appCode;
    @Value("${code.currency.base}")
    private String baseCode;
    @Value("${code.currency.unsup}")
    private String unsupCode;

    @Autowired
    private OpenExChangeRatesService openExChangeRatesService;

    @Autowired
    private GiphyService giphyService;

    @GetMapping("/check/{userCode}")
    SimpleGif checkAppCurrency(@PathVariable String userCode) {
        String yesterdayInString = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".json";
        ExchangeRateResponse todayExRate = openExChangeRatesService.getToday();
        ExchangeRateResponse yesterdayExRate = openExChangeRatesService.getYesterday(yesterdayInString);
        if (ExchangeRateUtil.isAppCurrencyWin(userCode.toUpperCase(), appCode, baseCode, todayExRate, yesterdayExRate)) {
            return giphyService.getRich().getData();
        }
        return giphyService.getBroke().getData();
    }


    @GetMapping("/codes")
    Map<String, String> getCurrencySymbols() {
        Map<String, String> result = openExChangeRatesService.getCurrencySymbols().getMap();
        result.remove(result.get(unsupCode));
        return result;
    }


}
