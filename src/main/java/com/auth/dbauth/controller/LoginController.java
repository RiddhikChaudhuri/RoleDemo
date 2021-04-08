package com.auth.dbauth.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.auth.dbauth.JwtUtils;
import com.auth.dbauth.server.dto.LoginDto;
import com.auth.dbauth.server.response.JwtResponse;

@RestController
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> signinUserAccount(@Valid @RequestBody LoginDto request) {
      Authentication authentication = authenticationManager
              .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
      logger.info("Checking for Sign In and Generating JWT Token");
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
              .collect(Collectors.toList());

      return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles,userDetails.isEnabled()));
  }
}
