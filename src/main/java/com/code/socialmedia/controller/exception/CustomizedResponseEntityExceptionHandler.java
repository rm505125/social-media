package com.code.socialmedia.controller.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {

		ErrorDetails erroDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(erroDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)
			throws Exception {

		ErrorDetails erroDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(erroDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

//		ErrorDetails erroDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
//				request.getDescription(false));
//		
		
//		ErrorDetails erroDetails = new ErrorDetails(LocalDateTime.now(), ex.getFieldError().getDefaultMessage(),
//				request.getDescription(false));  // for getting default methods
//		
		ErrorDetails erroDetails = new ErrorDetails(LocalDateTime.now(),
				"Total Errors:" + ex.getErrorCount()+  " First Errors:"+ ex.getMessage(),
				request.getDescription(false));
		
		
		return new ResponseEntity(erroDetails, HttpStatus.BAD_REQUEST);
	
	}
}
