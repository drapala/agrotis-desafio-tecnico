package br.com.agrotis.labapi.web.advice;

import br.com.agrotis.labapi.exception.LaboratoryAlreadyExistsException;
import br.com.agrotis.labapi.exception.LaboratoryNotFoundException;
import br.com.agrotis.labapi.web.response.ExceptionResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandlerAdvice {

    @ExceptionHandler(LaboratoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleLaboratoryAlreadyExistsException(final LaboratoryAlreadyExistsException ex) {
        return new ResponseEntity<>(new ExceptionResponse(409L, "Already exists laboratory with this name"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LaboratoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleLaboratoryNotFoundException(final LaboratoryNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(404L, "Not found laboratory with this id"), HttpStatus.NOT_FOUND);
    }
}
