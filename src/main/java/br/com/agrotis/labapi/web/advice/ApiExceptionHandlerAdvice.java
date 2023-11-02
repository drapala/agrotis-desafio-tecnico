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

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(final GenericException ex) {
        int statusCode = ex.getStatusCode();
        String message = ex.getMessage();
        return new ResponseEntity<>(new ExceptionResponse((long) statusCode, message), HttpStatus.valueOf(statusCode));
    }
}
