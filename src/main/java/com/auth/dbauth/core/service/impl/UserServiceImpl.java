package com.auth.dbauth.core.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.auth.dbauth.core.service.UserService;
import com.auth.dbauth.entity.AuthRole;
import com.auth.dbauth.entity.AuthUser;
import com.auth.dbauth.entity.Privilege;
import com.auth.dbauth.exception.DataFoundException;
import com.auth.dbauth.repository.PrivilageRepository;
import com.auth.dbauth.repository.RoleRepository;
import com.auth.dbauth.repository.UserRepository;
import com.auth.dbauth.server.dto.UserRegistrationDto;
import com.auth.dbauth.server.dto.UserRoleDto;
import com.auth.dbauth.server.dto.UserUpdateRoleDTO;
import com.auth.dbauth.util.PermEnum;
import com.auth.dbauth.util.RoleEnum;


@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  RoleRepository roleRepository;
  
  /** The password encoder. */
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  
  @Autowired
  private PrivilageRepository privilageRepository;


  @Override
  public Optional<AuthUser> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<String> findUseRolesByEmail(String email) {
    Optional<AuthUser> userEntity = findByEmail(email);
    List<String> roleList = new ArrayList<String>();
    if (userEntity.isPresent()) {
      userEntity.get().getRoles().stream().forEach(role -> roleList.add(role.getName()));
    }
    return roleList;
  }



  @Override
  public List<UserRoleDto> listAllUsers() {
    List<UserRoleDto> userRoleDTOList = new ArrayList<UserRoleDto>();
    AtomicInteger counter = new AtomicInteger(1);
    userRepository.findAll().stream().forEach(userRole -> {
      UserRoleDto userRoleDTO = new UserRoleDto();
      userRoleDTO.setSerialNumber(counter.getAndIncrement());
      userRoleDTO.setUsername(userRole.getEmail());
      userRoleDTO.setFullName(userRole.getFullname());
      List<String> userRolesList = new ArrayList<String>();
      userRole.getRoles().stream().forEach(role -> userRolesList.add(role.getName()));
      userRoleDTO.setRoleList(userRolesList);
      userRoleDTOList.add(userRoleDTO);
    });
    return userRoleDTOList;
  }


  /**
   * Load user by username.
   *
   * @param username the username
   * @return the user details
   */
  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<AuthUser> userEntity = findByEmail(username);
    if (userEntity.isPresent()) {
      List<SimpleGrantedAuthority> grantedAuthorities = getAuthorities(userEntity.get());

      return new org.springframework.security.core.userdetails.User(userEntity.get().getEmail(),
          userEntity.get().getPassword(), userEntity.get().isEnabled(), true, true, true,
          grantedAuthorities);
    }

    return null;
  }

  /**
   * Gets the authorities.
   *
   * @param user the user
   * @return the authorities
   */
  private List<SimpleGrantedAuthority> getAuthorities(AuthUser user) {
    List<AuthRole> rolesSet = user.getRoles();
    return rolesSet.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString()))
        .collect(Collectors.toList());
  }


  /**
   * Save user.
   *
   * @param user the user
   */
  @Override
  @Transactional
  public void saveUser(AuthUser user) {
    userRepository.save(user);

  }
  
  /**
   * Update user roles by username.
   *
   * @param role
   *            the role
   */
  @Override
  public void updateUserRolesByUsername(UserUpdateRoleDTO role) {
      if (findByEmail(role.getEmail()) != null) {
          Optional<AuthUser> userEntityOptional = findByEmail(role.getEmail());
          if(userEntityOptional.isPresent()) {
            AuthUser authUser = userEntityOptional.get();
          List<AuthRole> userRolesList = authUser.getRoles();
          role.getRoles().stream().forEach(newRole -> {
            if (!userRolesList.contains(newRole)) {
              userRolesList.add(roleRepository.findByName(newRole.getName()).get());
            }
          });
          
          authUser.setRoles(userRolesList);
          userRepository.save(authUser);

      }
      }else {
          throw new UsernameNotFoundException("Username Not Found");
      }

  }
  
  /**
   * Register.
   *
   * @param request
   *            the request
   * @throws DataFoundException
   *             the data found exception
   * @throws UnknownHostException
   *             the unknown host exception
   */
  @Transactional
  public void register(UserRegistrationDto request) throws DataFoundException {
    Optional<AuthUser> userOptional = userRepository.findByEmail(request.getEmail());
    if (userOptional.isPresent()) {
      throw new DataFoundException("emailExist,User exists by email");
    }
    AuthRole authRole = roleRepository.findByName(RoleEnum.REP.getDescription()).get();
    Privilege privilege = privilageRepository.findByAuthority("Add");
    if (privilege == null)
        privilege = privilageRepository.save(new Privilege("Add"));
    authRole.getPrivileges().add(privilege);
    AuthUser user = AuthUser.builder().email(request.getEmail()).enabled(true)
        .fullname(request.getFullName()).password(passwordEncoder.encode(request.getPassword()))
        .roles(Collections
            .singletonList(authRole))
        .build();


    saveUser(user);


  }

  @Override
  public void deleteRole(UserRoleDto userRoleDto) {
    Optional<AuthUser> userEntityOptional = findByEmail(userRoleDto.getUsername());
    if (userEntityOptional.isPresent()) {
      AuthUser authUser = userEntityOptional.get();
      List<AuthRole> userRolesList = authUser.getRoles();
      userRolesList = userRolesList.stream().filter(role -> {
        if (userRoleDto.getRoleList().contains(role.getName())) {
          return true;
        } else {
          return false;
        }
      }).collect(Collectors.toList());

      authUser.setRoles(userRolesList);
      userRepository.save(authUser);

    }
  }
  
  
  public void createAdmin(UserRegistrationDto request) {
    AuthRole role = roleRepository.findByName(RoleEnum.ADMIN.getDescription()).get();

    Privilege privilege = privilageRepository.findByAuthority(PermEnum.ADD.getDescription());
    if (privilege == null)
      privilege = privilageRepository.save(new Privilege(PermEnum.ADD.getDescription()));
    role.getPrivileges().add(privilege);
    privilege = privilageRepository.findByAuthority(PermEnum.UPDATE.getDescription());
    if (privilege == null)
      privilege = privilageRepository.save(new Privilege(PermEnum.UPDATE.getDescription()));
    role.getPrivileges().add(privilege);
    privilege = privilageRepository.findByAuthority(PermEnum.DELETE.getDescription());
    if (privilege == null)
      privilege = privilageRepository.save(new Privilege(PermEnum.DELETE.getDescription()));
    role.getPrivileges().add(privilege);
    AuthUser user = AuthUser.builder().email(request.getEmail()).enabled(true)
        .fullname(request.getFullName()).password(passwordEncoder.encode(request.getPassword()))
        .roles(Collections.singletonList(role)).build();
    userRepository.save(user);
  }

}
