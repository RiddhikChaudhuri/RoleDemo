package com.auth.dbauth.server.response;

import lombok.Data;

@Data
public class ApiResponse {

	private int statusCode;
	private boolean isError;
	private String uri;
	private String message;
	
	//api specific response
	private Object responseBody;
	
	private Object page;
	
	private Object links;
	
}
