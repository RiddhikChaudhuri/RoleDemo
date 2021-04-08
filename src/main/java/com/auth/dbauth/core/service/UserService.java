package com.auth.dbauth.core.service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.auth.dbauth.entity.AuthUser;
import com.auth.dbauth.exception.DataFoundException;
import com.auth.dbauth.server.dto.UserRegistrationDto;
import com.auth.dbauth.server.dto.UserRoleDto;
import com.auth.dbauth.server.dto.UserUpdateRoleDTO;


public interface UserService extends UserDetailsService {
	
    Optional<AuthUser> findByEmail(String email);

	void register(UserRegistrationDto user) throws DataFoundException;

	public List<String> findUseRolesByEmail(String email);

	public void saveUser(AuthUser user);

	//public boolean setNewPassword(String token, @Valid ConfirmPasswordDTO request);

	public List<UserRoleDto> listAllUsers();

	public void updateUserRolesByUsername(UserUpdateRoleDTO role);

  public void deleteRole(UserRoleDto userRoleDto);

	
}
