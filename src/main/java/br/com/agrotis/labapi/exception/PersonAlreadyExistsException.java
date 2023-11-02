package br.com.agrotis.labapi.exception;

public class PersonAlreadyExistsException extends GenericException {

    public PersonAlreadyExistsException() {
        super("Person already exists.", 409);
    }

    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
