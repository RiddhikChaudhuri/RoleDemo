package com.auth.dbauth.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth.dbauth.core.service.RoleService;
import com.auth.dbauth.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  RoleRepository roleRepository;
  
  
}
