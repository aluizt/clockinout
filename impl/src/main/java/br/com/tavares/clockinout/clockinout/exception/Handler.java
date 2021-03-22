package br.com.tavares.clockinout.clockinout.exception;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;

@RestControllerAdvice
public class Handler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorModel handleValidationExceptions(MethodArgumentNotValidException e) {
        return ErrorModel.builder()
                .message("Check the data provided !")
                .error(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApiException.class)
    public ErrorModel apiExceptionValidator(ApiException e) {
        return ErrorModel.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeException.class)
    public ErrorModel handleDateTimeException(DateTimeException e) {
        return ErrorModel.builder()
                .message("Check the reported time")
                .error(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorModel handleException(Exception e) {
        return ErrorModel.builder()
                .message("Try again, or contact our helpdesk")
                .error(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}

