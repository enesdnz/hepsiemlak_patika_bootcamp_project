package com.enesdeniz.authservice.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private HttpStatus httpStatus;
	private String message;
	private Date date;

	public ErrorResponse(HttpStatus httpStatus, String message, Date date) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.date = date;
	}

	public ErrorResponse(String message, Date date) {
		super();
		this.message = message;
		this.date = date;
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}

//	public HttpStatus getHttpStatus() {
//		return httpStatus;
//	}
//
//	public void setHttpStatus(HttpStatus httpStatus) {
//		this.httpStatus = httpStatus;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}

}
