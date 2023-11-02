package br.com.agrotis.labapi.web.advice;

import br.com.agrotis.labapi.exception.*;
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

    @ExceptionHandler(PropertyInfoAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handlePropertyInfoAlreadyExistsException(final PropertyInfoAlreadyExistsException ex) {
        return new ResponseEntity<>(new ExceptionResponse(409L, "Already exists property info with this name"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PropertyInfoNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePropertyInfoNotFoundException(final PropertyInfoNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(404L, "Not found property info with this id"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handlePersonAlreadyExistsException(final PersonAlreadyExistsException ex) {
        return new ResponseEntity<>(new ExceptionResponse(409L, "Already exists person info with this name"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePersonNotFoundException(final PersonNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(404L, "Not found person info with this id"), HttpStatus.NOT_FOUND);
    }
}