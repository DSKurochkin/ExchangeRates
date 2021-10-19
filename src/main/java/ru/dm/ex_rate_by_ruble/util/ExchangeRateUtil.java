package ru.dm.ex_rate_by_ruble.util;

import ru.dm.ex_rate_by_ruble.model.ExchangeRateResponse;
import ru.dm.ex_rate_by_ruble.util.exception.NotFoundCurrencyCodeException;

public class ExchangeRateUtil {
    public static boolean isAppCurrencyWin(String userCode, String appCode, String baseCode
            , ExchangeRateResponse todayExRate, ExchangeRateResponse yesterdayExRate) {
        if (userCode.equals(baseCode)) {
            return getRateByBase(todayExRate, appCode) > getRateByBase(yesterdayExRate, appCode);
        }
        if (!todayExRate.getRates().containsKey(userCode) || userCode.equals(appCode)) {
            throw new NotFoundCurrencyCodeException("Invalid or unsupported currency code");
        }

        return getRateByBase(todayExRate, appCode) - getRateByBase(yesterdayExRate, appCode)
                > getRateByBase(todayExRate, userCode) - getRateByBase(yesterdayExRate, userCode);
    }

    private static Double getRateByBase(ExchangeRateResponse exchangeRate, String userCode) {
        return exchangeRate.getRates().get(userCode);
    }
}

