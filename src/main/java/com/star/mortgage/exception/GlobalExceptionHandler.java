/**
 * 
 */
package com.star.mortgage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.star.mortgage.utility.ErrorConstant;

/**
 * @author User1
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidAccountException.class)
	public ResponseEntity<ErrorResponse> error(InvalidAccountException ex) {

		ErrorResponse er = new ErrorResponse();
		er.setMessage(ex.getMessage());
		er.setStatus(ErrorConstant.INVALID_ACCOUNT_CODE);
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorResponse> error(CustomerException ex) {

		ErrorResponse er = new ErrorResponse();
		er.setMessage(ex.getMessage());
		er.setStatus(ErrorConstant.NO_RECORD_FOUND_CODE);
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);

	}
	
	
	@ExceptionHandler(NoLoanFoundException.class)
	public ResponseEntity<ErrorResponse> error(NoLoanFoundException ex) {

		ErrorResponse er = new ErrorResponse();
		er.setMessage(ex.getMessage());
		er.setStatus(ErrorConstant.NO_LOAN_RECORD_CODE);
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);

	}
	
	
	

}
