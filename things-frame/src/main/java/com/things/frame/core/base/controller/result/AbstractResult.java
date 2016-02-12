package com.things.frame.core.base.controller.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResult implements Serializable {

	private static final long serialVersionUID = 2722255210873403195L;

	private Status status;

	private Object message;

	public AbstractResult() {
		super();
	}

	public AbstractResult(Status status, Object message) {
		this.status = status;
		this.message = message;
	}

	public enum Status {
		OK("OK"),                //完成
		ERROR("ERROR");   //错误
		
		 private String text;
	    
		Status(String text) {
	        this.text = text;
	    }
		
	    private static final Map<String, Status> stringToEnum = new HashMap<String, Status>();
	    static {
	        for(Status statu : values()) {
	            stringToEnum.put(statu.toString(), statu);
	        }
	    }
	    
	    public static Status fromString(String symbol) {
	        return stringToEnum.get(symbol);
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}

	public void addOK(Object message) {
		this.message = message;
		this.status = Status.OK;
	}

	public void addError(Object message) {
		this.message = message;
		this.status = Status.ERROR;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
	
	public abstract String toString();
	
}
