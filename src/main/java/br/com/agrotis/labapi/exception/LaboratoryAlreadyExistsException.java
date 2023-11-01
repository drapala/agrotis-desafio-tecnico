package br.com.agrotis.labapi.exception;

public class LaboratoryAlreadyExistsException extends RuntimeException {

    public LaboratoryAlreadyExistsException(){
        super();
    }

    public LaboratoryAlreadyExistsException(String message){
        super(message);
    }
}
