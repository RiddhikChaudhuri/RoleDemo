package com.auth.dbauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);



  @GetMapping("")
  public String viewHomePage() {
      return "index";
  }
}
