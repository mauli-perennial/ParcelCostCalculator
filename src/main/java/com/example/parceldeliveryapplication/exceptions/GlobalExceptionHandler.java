package com.example.parceldeliveryapplication.exceptions;

import com.example.parceldeliveryapplication.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
Exception across the application will be handled by this class.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * @param e This will be the object of the exception occurred at the run time;
     * @return It returns the exception message whenever exception occurs .
     */
    @ExceptionHandler(InvalidParcelException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(InvalidParcelException e) {
        ErrorResponse resp = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }

    /**
     * @param e This will be the object of the exception occurred at the run time;
     * @return It returns the exception message whenever exception occurs .
     */
    @ExceptionHandler(InvalidVoucherException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(InvalidVoucherException e) {
        ErrorResponse resp = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }

    /**
     * @param e This will be the object of the exception occurred at the run time;
     * @return It returns the exception message whenever exception occurs .
     */
    @ExceptionHandler(BadVoucherException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(BadVoucherException e) {
        ErrorResponse resp = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }
}
