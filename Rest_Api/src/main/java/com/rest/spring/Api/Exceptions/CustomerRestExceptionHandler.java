package com.rest.spring.Api.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleExEntity(CustomerNotFoundException exc)
	{
		// create CustomerErrorResponse
		
		CustomerErrorResponse errorResponse= new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
		
		
		// return ResponseEntity
		
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		
		
	} 
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleExEntity(CustomerBadIndexException exc)
	{
		// create CustomerErrorResponse
		
		CustomerErrorResponse errorResponse= new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
		
		
		// return ResponseEntity
		
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
		
		
	}
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleExEntity(TransferNotAllowedException exc)
	{
		// create CustomerErrorResponse
		
		CustomerErrorResponse errorResponse= new CustomerErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), exc.getMessage(), System.currentTimeMillis());
		
		
		// return ResponseEntity
		
		return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);
		
		
	}
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleExEntity(InformativeMessageException exc)
	{
		// create CustomerErrorResponse
		
		CustomerErrorResponse errorResponse= new CustomerErrorResponse(HttpStatus.OK.value(), exc.getMessage(), System.currentTimeMillis());
		
		
		// return ResponseEntity
		
		return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);
		
		
	}

	
}
