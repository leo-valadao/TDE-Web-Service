package com.leonardo.tde.error;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handlerNotFoundException(NotFoundException exception, HttpServletRequest request) {
        return new Error(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        HashMap<String, String> listaErros = new HashMap<>();

        for (FieldError fieldError : exception.getFieldErrors()) {
            listaErros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "Requisição Inválida!",
                request.getRequestURI());

        error.setHashMap(listaErros);

        return error;
    }
}
