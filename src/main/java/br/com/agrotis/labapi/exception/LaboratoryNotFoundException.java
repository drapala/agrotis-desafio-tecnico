package br.com.agrotis.labapi.exception;

public class LaboratoryNotFoundException extends RuntimeException {

    public LaboratoryNotFoundException(){
        super();
    }

    public LaboratoryNotFoundException(String message){
        super(message);
    }
}
