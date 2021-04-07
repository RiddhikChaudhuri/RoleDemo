package com.auth.dbauth.repository;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.auth.dbauth.entity.AuthRole;

@Repository
public interface RoleRepository extends JpaRepository<AuthRole, Integer>{

  public Optional<AuthRole> findByRoleName(String roleName);
  
  public Set<AuthRole> findAllByRoleIdIn(Set<Integer> idList);


}
