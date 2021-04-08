package com.auth.dbauth.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BlackListedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8025262967770127402L;

	public BlackListedException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
}
