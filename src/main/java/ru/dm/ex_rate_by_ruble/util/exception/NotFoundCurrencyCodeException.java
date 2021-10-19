package ru.dm.ex_rate_by_ruble.util.exception;

public class NotFoundCurrencyCodeException extends RuntimeException {
    public NotFoundCurrencyCodeException(String message) {
        super(message);
    }
}
