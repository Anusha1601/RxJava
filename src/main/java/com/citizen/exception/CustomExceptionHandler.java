package com.citizen.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.citizen.model.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ErrorResponse error = new ErrorResponse("Error", ex.getLocalizedMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> entityNotFoundException(Exception ex) {
        
        ErrorResponse error = new ErrorResponse("Not Found", ex.getLocalizedMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(CustomerGenericException.class)
    public final ResponseEntity<Object> customerGenericException(CustomerGenericException ex) {
        
        ErrorResponse error = new ErrorResponse("Not Found", ex.getLocalizedMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
	
	

}
