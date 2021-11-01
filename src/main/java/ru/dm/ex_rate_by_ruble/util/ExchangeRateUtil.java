package ru.dm.ex_rate_by_ruble.util;

import ru.dm.ex_rate_by_ruble.model.ExchangeRateResponse;
import ru.dm.ex_rate_by_ruble.util.exception.NotFoundCurrencyCodeException;

public class ExchangeRateUtil {
    public static boolean isAppCurrencyWon(String userCode, String appCode, String baseCode
            , ExchangeRateResponse todayExRate, ExchangeRateResponse yesterdayExRate) {
        if (userCode.equals(baseCode)) {
            return getRateInBaseCurrency(todayExRate, appCode) > getRateInBaseCurrency(yesterdayExRate, appCode);
        }
        if (!todayExRate.getRates().containsKey(userCode) || userCode.equals(appCode)) {
            throw new NotFoundCurrencyCodeException("Invalid or unsupported currency code");
        }

        return getRateInBaseCurrency(todayExRate, appCode) - getRateInBaseCurrency(yesterdayExRate, appCode)
                > getRateInBaseCurrency(todayExRate, userCode) - getRateInBaseCurrency(yesterdayExRate, userCode);
    }

    private static Double getRateInBaseCurrency(ExchangeRateResponse exchangeRate, String userCode) {
        return 1 / exchangeRate.getRates().get(userCode);
    }
}

