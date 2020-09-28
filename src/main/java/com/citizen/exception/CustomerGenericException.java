package com.citizen.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerGenericException extends RuntimeException{
	
	
	    public CustomerGenericException(String exception) {
	        super(exception);
	    }

}
