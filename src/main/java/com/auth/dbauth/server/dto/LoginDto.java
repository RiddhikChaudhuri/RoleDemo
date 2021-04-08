package com.auth.dbauth.server.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * The Class LoginDto.
 */
public class LoginDto {

	/** The email. */
	@Email
	@NotBlank
	private String email;

	/** The password. */
	@NotBlank
	private String password;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
