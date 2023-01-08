package com.code.socialmedia.controller.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails {

	// timestamp
	// message
	// details
	private LocalDateTime timestamp;
	private String message;
	private String details;

	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}