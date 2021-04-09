package com.auth.dbauth.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.auth.dbauth.core.service.UserService;
import com.auth.dbauth.server.dto.UserRoleDto;

@Controller
public class UsersController {

  @Autowired
  UserService userService;
  
  @GetMapping("/users")
  public String listUsers(Model model) {
      List<UserRoleDto> listUsers = userService.listAllUsers();
      model.addAttribute("listUsers", listUsers);
      
      return "users";
  }
}
