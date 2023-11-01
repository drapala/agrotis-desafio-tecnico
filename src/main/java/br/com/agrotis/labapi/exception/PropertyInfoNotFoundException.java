package br.com.agrotis.labapi.exception;

public class PropertyInfoNotFoundException extends RuntimeException {

    public PropertyInfoNotFoundException() {
        super();
    }

    public PropertyInfoNotFoundException(String message) {
        super(message);
    }
}
