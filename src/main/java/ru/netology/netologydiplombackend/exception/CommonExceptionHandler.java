package ru.netology.netologydiplombackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.netologydiplombackend.model.ErrorResponse;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ErrorResponse> invalidCredentialsHandler(InvalidCredentials e) {
        return getResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(Exception e) {
        return getResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<ErrorResponse> getResponseEntity(Exception e, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), status.value()), status);
    }
}
