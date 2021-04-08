package com.auth.dbauth.server.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

	@Size(min = 6, max = 120)
	@NotBlank
	private String fullName;

	@NotBlank
	private String password;

	

	@Size(min = 6, max = 200)
	@Email
	@NotBlank
	private String email;


	

}