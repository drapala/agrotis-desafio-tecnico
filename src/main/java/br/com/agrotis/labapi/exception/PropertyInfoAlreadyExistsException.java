package br.com.agrotis.labapi.exception;

public class PropertyInfoAlreadyExistsException extends RuntimeException {

    public PropertyInfoAlreadyExistsException() {
        super();
    }

    public PropertyInfoAlreadyExistsException(String message) {
        super(message);
    }
}
