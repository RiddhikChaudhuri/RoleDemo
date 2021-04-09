package com.auth.dbauth.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.auth.dbauth.core.service.UserService;
import com.auth.dbauth.core.util.CoreUtil;
import com.auth.dbauth.server.dto.UserRoleDto;
import com.auth.dbauth.server.dto.UserUpdateRoleDTO;
import com.auth.dbauth.server.response.ApiResponse;
import com.auth.dbauth.util.RoleEnum;

@RestController
@RequestMapping(path = "/roles")
public class RoleController {

  @Autowired
  UserService userService;

  @GetMapping("/current-user")
  public ResponseEntity<ApiResponse> getCurrentUserRoles(HttpServletRequest httpServletRequest) {

    return new ResponseEntity(userService.findUseRolesByEmail(CoreUtil.getCurrentUserName()),
        HttpStatus.OK);
  }

  @PutMapping("/add-role")
  @PreAuthorize("hasAuthority('Update')")
  @Secured(RoleEnum.Code.ADMIN)
  public ResponseEntity addUserRole(HttpServletRequest httpServletRequest,
      UserUpdateRoleDTO userRoleDto) {
    userService.updateUserRolesByUsername(userRoleDto);

    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping("/delete-role")
  @PreAuthorize("hasAuthority('Delete')")
  @Secured(RoleEnum.Code.ADMIN)
  public ResponseEntity deleteUserRole(HttpServletRequest httpServletRequest,
      UserRoleDto userRoleDto) {
    userService.deleteRole(userRoleDto);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/list-all")
  public ResponseEntity listAllUserRoles(HttpServletRequest httpServletRequest,
      @RequestParam("emailID") String email) {
    return new ResponseEntity(userService.findUseRolesByEmail(email), HttpStatus.OK);
  }
}
