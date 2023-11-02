package br.com.agrotis.labapi.exception;

public class PropertyInfoAlreadyExistsException extends GenericException {

    public PropertyInfoAlreadyExistsException() {
        super("Property info already exists.", 409);
    }

    public PropertyInfoAlreadyExistsException(String message) {
        super(message);
    }
}
