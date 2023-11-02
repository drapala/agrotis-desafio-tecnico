package br.com.agrotis.labapi.exception;

public class LaboratoryNotFoundException extends GenericException {

    public LaboratoryNotFoundException() {
        super("Laboratory not found.", 404);
    }

    public LaboratoryNotFoundException(String message) {
        super(message);
    }
}
