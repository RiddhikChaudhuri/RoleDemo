package com.auth.dbauth.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auth.dbauth.server.dto.UserRoleDto;
import com.auth.dbauth.server.response.ApiResponse;
import com.auth.dbauth.util.RoleEnum;

@RestController
@RequestMapping(path = "/roles")
public class RoleController {


  @GetMapping("/current-user")
  public ResponseEntity<ApiResponse> getCurrentUserRoles(HttpServletRequest httpServletRequest) {
    return null;
  }

  @PostMapping("/add-role")
  @Secured(RoleEnum.Code.ADMIN)
  public ResponseEntity<ApiResponse> addUserRole(HttpServletRequest httpServletRequest,
      UserRoleDto userRoleDto) {
    return null;
  }

  @DeleteMapping("/delete-role")
  public ResponseEntity<ApiResponse> deleteUserRole(HttpServletRequest httpServletRequest,
      UserRoleDto userRoleDto) {
    return null;
  }

  @GetMapping("/list-all")
  public ResponseEntity<ApiResponse> listAllUserRoles(HttpServletRequest httpServletRequest) {
    return null;
  }
}
