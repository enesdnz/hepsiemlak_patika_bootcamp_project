package com.enesdeniz.authservice.advice;

import java.util.Date;

import com.enesdeniz.authservice.exception.EmlakBuradaException;
import com.enesdeniz.authservice.exception.UserNotFoundException;
import com.enesdeniz.authservice.exception.UserPasswordNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handle(UserNotFoundException exception) {
		log.error("User not found exception occured." + exception.getMessage());
		ErrorResponse response = new ErrorResponse(exception.getMessage(), new Date());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserPasswordNotValidException.class)
	public ResponseEntity<ErrorResponse> handle(UserPasswordNotValidException exception) {
		log.error("User password not valid exception occured." + exception.getMessage());
		ErrorResponse response = new ErrorResponse(exception.getMessage(), new Date());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmlakBuradaException.class)
	public ResponseEntity<ErrorResponse> handle(EmlakBuradaException exception) {
		log.error("General EmlakBuradaException exception occured." + exception.getMessage());
		ErrorResponse response = new ErrorResponse(exception.getMessage(), new Date());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> handle(NullPointerException exception) {
		log.error("NullPointerException exception occured." + exception.getMessage());
		ErrorResponse response = new ErrorResponse("yolunda gitmeyen işler oldu.", new Date());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
