package com.capstone.CapstoneServer.exception;


import java.time.LocalDateTime;


public class ExceptionResponse{

    private int errorCode;
    private String message;
    private LocalDateTime dateTime;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}