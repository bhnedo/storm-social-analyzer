package org.securityartwork.storm.social.analyzer.common;

public class Status {
	
	private String message;
	private String from;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public Status() {
		
	}
	
	public Status(String message, String from) {
		this.message = message;
		this.from = from;
	}
}
