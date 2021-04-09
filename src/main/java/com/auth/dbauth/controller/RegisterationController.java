package com.auth.dbauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.auth.dbauth.core.service.UserService;
import com.auth.dbauth.entity.AuthUser;
import com.auth.dbauth.exception.DataFoundException;
import com.auth.dbauth.server.dto.UserRegistrationDto;

@Controller
public class RegisterationController {

  
  @Autowired
  private UserService userService;
  
  
  
  @PostMapping("/process_register")
  public String processRegister(UserRegistrationDto user) throws DataFoundException {
    this.userService.register(user);

    return "register_success";
  }
  
  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
      model.addAttribute("user", new AuthUser());
      
      return "signup_form";
  }
}
