package com.auth.dbauth.core.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth.dbauth.entity.AuthRole;
import com.auth.dbauth.entity.AuthUser;
import com.auth.dbauth.repository.RoleRepository;
import com.auth.dbauth.repository.UserRepository;
import com.auth.dbauth.util.RoleEnum;

@Service
public class BootstrapService {

  @Autowired
  private RoleRepository roleRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private BCryptPasswordEncoder encoder;

  public void execute() {
    createRole();
    createUser();
  }

  private void createRole() {
    RoleEnum roles[] = RoleEnum.values();
    Arrays.asList(roles).forEach(roleName -> {
      String roleNameStr = roleName.getDescription();

      Optional<AuthRole> roleOp = roleRepository.findByName(roleNameStr);
      if (roleOp.isPresent()) {
        // role is already present don't do anything
      } else {
        AuthRole role;
        boolean isAdmin = false;
        if (RoleEnum.ADMIN.equals(roleName)) {
          isAdmin = true;
        }
        role = AuthRole.builder().name(roleNameStr).isAdmin(isAdmin).build();
        roleRepository.save(role);
      }
    });
  }
  
  
  private void createUser() {
    if (userRepository.count() == 0) {
      List<AuthRole> roleList = roleRepository.findAll();

      AuthUser user = AuthUser.builder().enabled(true).username("riddhik@gmail.com")
          .email("riddhik@gmail.com").password(encoder.encode("abcd123")).roles(roleList).build();
      userService.saveUser(user);
    }

  }
}
