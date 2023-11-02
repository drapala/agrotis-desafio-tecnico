package br.com.agrotis.labapi.exception;

public class LaboratoryAlreadyExistsException extends GenericException {

    public LaboratoryAlreadyExistsException() {
        super("Laboratory already exists.", 409);
    }

    public LaboratoryAlreadyExistsException(String message) {
        super(message);
    }
}
