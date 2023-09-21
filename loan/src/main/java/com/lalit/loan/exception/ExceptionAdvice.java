package com.lalit.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lalit.loan.model.LoanError;


@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(LoanIdException.class)
	public ResponseEntity<LoanError> mapException(LoanIdException ex) {
		LoanError error = new LoanError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<LoanError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomerIdException.class)
	public ResponseEntity<LoanError> mapException(CustomerIdException ex) {
		LoanError error = new LoanError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<LoanError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(LenderIdException.class)
	public ResponseEntity<LoanError> mapException(LenderIdException ex) {
		LoanError error = new LoanError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<LoanError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DueDateExpiredException.class)
	public ResponseEntity<LoanError> mapException(DueDateExpiredException ex) {
		LoanError error = new LoanError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<LoanError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
