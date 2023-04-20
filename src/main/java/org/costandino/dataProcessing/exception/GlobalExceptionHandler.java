package org.costandino.dataProcessing.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException() {
        return ResponseEntity
                .notFound()
                .build();
    }

    @ExceptionHandler(SchemaException.class)
    public ResponseEntity<?> handleSchemaException(SchemaException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

}
