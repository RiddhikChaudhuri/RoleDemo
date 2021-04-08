package com.auth.dbauth.controller;

import java.net.UnknownHostException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.auth.dbauth.core.service.UserService;
import com.auth.dbauth.exception.DataFoundException;
import com.auth.dbauth.server.dto.UserRegistrationDto;
import com.auth.dbauth.util.RoleEnum;

@RestController
public class RegisterationController {

  
  @Autowired
  private UserService userService;
  
  @PostMapping("signup")
  @Secured({RoleEnum.Code.ADMIN,RoleEnum.Code.MANAGER})
  public void create(@Valid @RequestBody UserRegistrationDto request)
          throws DataFoundException, UnknownHostException {
      this.userService.register(request);
  }
}
