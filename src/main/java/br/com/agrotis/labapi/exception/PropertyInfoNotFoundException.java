package br.com.agrotis.labapi.exception;

public class PropertyInfoNotFoundException extends GenericException {

    public PropertyInfoNotFoundException() {
        super("Property info not found.", 404);
    }

    public PropertyInfoNotFoundException(String message) {
        super(message);
    }
}
