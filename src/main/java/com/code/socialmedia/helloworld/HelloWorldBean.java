package com.code.socialmedia.helloworld;

public class HelloWorldBean {

	public HelloWorldBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

}
