package br.com.agrotis.labapi.exception;

public class PersonNotFoundException extends GenericException {

    public PersonNotFoundException() {
        super("Person not found.", 404);
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
