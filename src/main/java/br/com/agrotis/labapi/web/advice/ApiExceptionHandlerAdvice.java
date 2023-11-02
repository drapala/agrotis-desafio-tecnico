package br.com.agrotis.labapi.web.advice;

import br.com.agrotis.labapi.exception.GenericException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandlerAdvice {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<String> handleGenericException(final GenericException ex) {
        return buildResponse(ex.getStatusCode(), ex.getMessage());
    }

    private ResponseEntity<String> buildResponse(final int HttpStatus, final String message) {
        return ResponseEntity.status(HttpStatus)
                .body(message);
    }
}
