package com.auth.dbauth.server.response;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	public final String email;
	public final List<String> roles;
	boolean enabled;

	public JwtResponse(String jwt, String username, List<String> roles, boolean enabled) {
		this.jwttoken = jwt;
		this.email = username;
		this.roles = roles;
		this.enabled = enabled;
	}

	public String getToken() {
		return this.jwttoken;
	}
}