package br.com.agrotis.labapi.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

    private int statusCode;

    public GenericException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public GenericException(String message) {
        super(message);
    }
}
