package ru.dm.ex_rate_by_ruble;


import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dm.ex_rate_by_ruble.util.exception.ErrorInfo;
import ru.dm.ex_rate_by_ruble.util.exception.ErrorType;
import ru.dm.ex_rate_by_ruble.util.exception.NotFoundCurrencyCodeException;

import javax.servlet.http.HttpServletRequest;

import static ru.dm.ex_rate_by_ruble.util.exception.ErrorType.APP_ERROR;
import static ru.dm.ex_rate_by_ruble.util.exception.ErrorType.NOT_FOUND_CODE;


@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {

    private static ErrorInfo getErrorInfo(HttpServletRequest req, Exception e, ErrorType errorType, String... details) {
        return new ErrorInfo(req.getRequestURL(), errorType,
                details.length != 0 ? details : new String[]{getMessage(getRootCause(e))});
    }

    private static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

    private static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundCurrencyCodeException.class)
    public ErrorInfo unsupportedTimeError(HttpServletRequest req, NotFoundCurrencyCodeException e) {
        return getErrorInfo(req, e, NOT_FOUND_CODE);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return getErrorInfo(req, e, APP_ERROR);
    }
}